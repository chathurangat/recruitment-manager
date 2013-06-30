package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cv_template")
public class CvTemplateController {

    private static final Logger  logger = LoggerFactory.getLogger(CvTemplateController.class);

    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;
    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;

    //    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view",method = RequestMethod.GET)
    public ModelAndView CvTemplateRegisterView(ModelAndView modelAndView){
        System.out.println(" cv template registration view ");
        List<CvApplicationSection> cvApplicationSectionList = cvApplicationSectionService.findAllCvSection();

        Map<String,Object> modelObjects = new HashMap<String, Object>();

        List<Integer> priorityList = this.createPriorityLit(cvApplicationSectionList);
        modelAndView.setViewName("cv-template/cv-template-register");
        modelObjects.put("masterCvApplicationSectionList", cvApplicationSectionList);
        CvApplicationTemplate cvApplicationTemplate =  new CvApplicationTemplate();
        modelObjects.put("cvApplicationTemplate",cvApplicationTemplate);
        modelObjects.put("priorityList",priorityList);
        modelAndView.addAllObjects(modelObjects);
        return modelAndView;
    }


    //    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerNewCvTemplate(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,ModelAndView modelAndView,HttpServletRequest request){

        System.out.println(" size of cvApplicationSectionList size ["+cvApplicationTemplate.getCvApplicationSectionList().size()+"]");
        List<CvApplicationSection> selectedCvApplicationSectionList = new ArrayList<CvApplicationSection>();
        Map<String,String> errorMessages = new HashMap<String,String>();

        this.findSelectedCvApplicationListForCvTemplate(cvApplicationTemplate,selectedCvApplicationSectionList,errorMessages);

        System.out.println(" number of selected Cv sections for the template is ["+selectedCvApplicationSectionList.size()+"]");
        System.out.println(" number of error founds with submitted form ["+errorMessages.size()+"]  ");

        if(errorMessages.size()>0){
            //binding each error message to the relevant field
            for(Map.Entry<String,String> entry:errorMessages.entrySet()){
                bindingResult.addError(new FieldError("cvApplicationTemplate",entry.getKey(),entry.getValue()));
            }
        }
        //todo at least one section should be selected
        //creating or updating the CV Template
        if(!bindingResult.hasErrors()){
            logger.info(" submitted form contains valid data ");
            cvApplicationTemplate.setCvApplicationSectionList(selectedCvApplicationSectionList);
//            modelAndView.setViewName("redirect:cv-template-register-success");
//            if(StringUtils.hasText(cvApplicationTemplate.getId()))
//            {
//                cvApplicationTemplateService.update(cvApplicationTemplate);
//                logger.info("update the cv application template");
//            }
//            else
//            {
//                cvApplicationTemplateService.create(cvApplicationTemplate);
//                logger.info("created new cv application template");
//            }
            modelAndView = createOrUpdateCvTemplate(cvApplicationTemplate);
            if(cvApplicationTemplate.getId()!=null){
                request.getSession().setAttribute("last-created-cv-template-id", cvApplicationTemplate.getId());
            }
        }
        else{
            logger.info(" submitted form contains field errors ");
            //loading the UI again
//            List<CvApplicationSection> cvApplicationSectionList = cvApplicationSectionService.findAllCvSection();
//            Map<String,Object> modelObjects = new HashMap<String, Object>();
//
//            List<Integer> priorityList = this.createPriorityLit(cvApplicationSectionList);
//            modelAndView.setViewName("cv-template/cv-template-register");
//            modelObjects.put("masterCvApplicationSectionList", cvApplicationSectionList);
//            modelObjects.put("priorityList",priorityList);
//            modelAndView.addAllObjects(modelObjects);
            modelAndView = this.initializeCvTemplateRegistrationView();
        }
        return modelAndView;
    }

    //    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv-template-register-success")
    public ModelAndView cvApplicationTemplateRegistrationSuccess(HttpServletRequest request){
        String lastCreatedCvTemplateId = (String) request.getSession().getAttribute("last-created-cv-template-id");
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById(lastCreatedCvTemplateId);
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
        modelAndView.setViewName("cv-template/cv-template-register-success");
        return modelAndView;
    }

    private ModelAndView createOrUpdateCvTemplate(CvApplicationTemplate cvApplicationTemplate){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:cv-template-register-success");
        if(StringUtils.hasText(cvApplicationTemplate.getId()))
        {
            cvApplicationTemplateService.update(cvApplicationTemplate);
            logger.info("update the cv application template");
        }
        else
        {
            cvApplicationTemplateService.create(cvApplicationTemplate);
            if(cvApplicationTemplate.getId()==null || (cvApplicationTemplate.getId()!=null && cvApplicationTemplate.getId().trim().equals(""))){
                logger.info(" new cv template was not created successfully ");
                 modelAndView = this.initializeCvTemplateRegistrationView();
                 modelAndView.addObject("error",true);
            }
            else{
                logger.info("created new cv application template");
            }
        }
        return modelAndView;
    }


    private ModelAndView initializeCvTemplateRegistrationView(){
        ModelAndView modelAndView = new ModelAndView();
        List<CvApplicationSection> cvApplicationSectionList = cvApplicationSectionService.findAllCvSection();
        Map<String,Object> modelObjects = new HashMap<String, Object>();

        List<Integer> priorityList = this.createPriorityLit(cvApplicationSectionList);
        modelAndView.setViewName("cv-template/cv-template-register");
        modelObjects.put("masterCvApplicationSectionList", cvApplicationSectionList);
        modelObjects.put("priorityList",priorityList);
        modelAndView.addAllObjects(modelObjects);
        return modelAndView;
    }

    /**
     * <P>
     *  this will find the list of {@link CvApplicationSection} selected for the CvTemplate being created/updated.
     *  the selected CvSections list will be added to the List passed as  @param selectedCvApplicationSectionList
     *  if there are errors found with related to cv sections and their priorities,errorMessages Map will be updated
     * </P>
     * @param cvApplicationTemplate will be the model object that encapsulates all the submitted data by the user
     * @param selectedCvApplicationSectionList will be modified inside the method to hold the list of {@link CvApplicationSection} instances selected by the user for the CvTemplate being created
     * @param errorMessages  will be modified inside the method to hold the error messages related to the cv sections and their priories
     */
    private void findSelectedCvApplicationListForCvTemplate(CvApplicationTemplate cvApplicationTemplate,List<CvApplicationSection> selectedCvApplicationSectionList,Map<String,String> errorMessages){
        //this map will hold a set of entered priorities for each cv application section
        Map<Integer,Integer> enteredPriorities = new HashMap<Integer, Integer>();

        int index= 0;
        for(CvApplicationSection cvApplicationSection:cvApplicationTemplate.getCvApplicationSectionList()){
            if(cvApplicationSection.getId()!=null){
                //finding the original instance by giving Id
                CvApplicationSection cvApplicationSection1 = cvApplicationSectionService.findCvSectionById(cvApplicationSection.getId());
                if(cvApplicationSection1!=null){
                    logger.info(" user has selected cv section [{}] for the cv template",cvApplicationSection1.getSectionNameEn());
                    //setting up the user entered priority
                    cvApplicationSection1.setPriority(cvApplicationSection.getPriority());
                    selectedCvApplicationSectionList.add(cvApplicationSection1);
                }
                logger.info(" cv section has been submitted with id [{}] and priority [{}]",cvApplicationSection.getId(),cvApplicationSection.getPriority());
                //checking the validity of the submitted priority
                this.validateSubmittedPriority(cvApplicationSection.getPriority(),index,enteredPriorities,errorMessages);
            }
            index++;
        }
        //check whether user has selected at least one cv section fr the cv template
        if(selectedCvApplicationSectionList.size()==0){
            //adding custom error message here
            errorMessages.put("cvApplicationSectionList","At least One CV Section should be selected ");

        }
    }


    /**
     * <p>
     *     checking the validity of the submitted priority
     * </p>
     * @param cvSectionPriority is the priority entered by the user for the cv section
     * @param cvSectionIndex is the index location of the cv sections that resides in the cv section list for the template. this will help to compose the validation error message if there is any
     * @param priorityMap will contain a map of priorities inserted for the cv section list. (key->priority and value-> cvSectionIndex)
     * @param errorMessages hold the map of error messages (if any) related to the cv section priority
     */
    private void validateSubmittedPriority(int cvSectionPriority,int cvSectionIndex,Map<Integer,Integer> priorityMap,Map<String,String> errorMessages){
        if(cvSectionPriority!=-1){
            if(!priorityMap.containsKey(cvSectionPriority)){
                //priority is not duplicated so far
                logger.info(" priority is not duplicated so far");
                priorityMap.put(cvSectionPriority,cvSectionIndex);
            }
            else {
                logger.info("duplicate priority was found");
                //error message for the current duplicate priority
                errorMessages.put("cvApplicationSectionList["+cvSectionIndex+"].priority","Duplicates priorities were found ");
                //error message for the the already inserted duplicate priority
                errorMessages.put("cvApplicationSectionList["+priorityMap.get(cvSectionPriority)+"].priority","Duplicates priorities were found ");
            }
        }
        else{
            errorMessages.put("cvApplicationSectionList["+cvSectionIndex+"].priority","Priority should be selected ");
        }
    }

    /**
     * <p>
     *     creating the priority list based on the applicationSectionList
     * </p>
     * @param cvApplicationSectionList contains the list of {@link CvApplicationSection} instances found
     * @return list of priorities based on the size of the cv application section list
     */
    private List<Integer> createPriorityLit(List<CvApplicationSection> cvApplicationSectionList){
        List<Integer> priorityList = new ArrayList<Integer>();
        if(cvApplicationSectionList!=null){
            for(int i=1;i<=cvApplicationSectionList.size();i++){
                priorityList.add(i);
            }
        }
        return priorityList;
    }

}
