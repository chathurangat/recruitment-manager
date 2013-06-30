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
    public ModelAndView registerNewCvTemplate(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,ModelAndView modelAndView){

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

//        //check whether user has selected at least one cv section fr the cv template
//        if(selectedCvApplicationSectionList.size()==0){
//            //adding custom error message here
//            bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList","At least One CV Section should be selected "));
//        }
//
//        //check for priority duplicates
//        if(duplicatePriorityIndexes.size()!=0){
//            for(Integer itemIndex :duplicatePriorityIndexes){
//                //adding custom error message here
//                bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList["+itemIndex+"].priority","Duplicates priorities were found "));
//            }
//        }
        //todo at least one section should be selected
        //creating or updating the CV Template
        if(!bindingResult.hasErrors()){
            logger.info(" submitted form does not contain field errors ");
            cvApplicationTemplate.setCvApplicationSectionList(selectedCvApplicationSectionList);
            modelAndView.setViewName("redirect:cv-template-register-success");
            modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
            if(StringUtils.hasText(cvApplicationTemplate.getId()))
            {
                cvApplicationTemplateService.update(cvApplicationTemplate);
                logger.info("updating the cv application template");
//                modelAndView.setViewName("redirect:cv-template/cv-template-register-success");
            }
            else
            {
                cvApplicationTemplateService.create(cvApplicationTemplate);
                logger.info("creating new cv application template");
//                modelAndView.setViewName("redirect:cv-template/cv-template-register-success");
            }
        }
        else{
            logger.info(" submitted form contains field errors ");
            //loading the UI again
            List<CvApplicationSection> cvApplicationSectionList = cvApplicationSectionService.findAllCvSection();
            Map<String,Object> modelObjects = new HashMap<String, Object>();

            List<Integer> priorityList = this.createPriorityLit(cvApplicationSectionList);
            modelAndView.setViewName("cv-template/cv-template-register");
            modelObjects.put("masterCvApplicationSectionList", cvApplicationSectionList);
            modelObjects.put("priorityList",priorityList);
            modelAndView.addAllObjects(modelObjects);
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


    /**
     * <P>
     *  this will find the list of {@link CvApplicationSection} selected for the CvTemplate being created/updated.
     *  the selected CvSections list will be added to the List passed as  @param selectedCvApplicationSectionList
     *  if there are duplicate priorities found, relevant Cv section indexes will be updated in the duplicatePriorityIndexes list.
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
                //setting up the user entered priority
                cvApplicationSection1.setPriority(cvApplicationSection.getPriority());
                selectedCvApplicationSectionList.add(cvApplicationSection1);

                logger.info(" cv section has been submitted with id [{}] and priority [{}]",cvApplicationSection.getId(),cvApplicationSection.getPriority());

                if(!enteredPriorities.containsKey(cvApplicationSection.getPriority())){
                    //priority is not duplicated so far
                    logger.info(" priority is not duplicated so far");
                    enteredPriorities.put(cvApplicationSection.getPriority(),index);
                }
                else {
                    logger.info("duplicate priority was found");
                    //error message for the current duplicate priority
//                    duplicatePriorityIndexes.add(index);
                    errorMessages.put("cvApplicationSectionList["+index+"].priority","Duplicates priorities were found ");

                    //error message for the the already inserted duplicate priority
//                    duplicatePriorityIndexes.add(enteredPriorities.get(cvApplicationSection.getPriority()));
                    errorMessages.put("cvApplicationSectionList["+enteredPriorities.get(cvApplicationSection.getPriority())+"].priority","Duplicates priorities were found ");
                }
            }
            index++;
        }

        //check whether user has selected at least one cv section fr the cv template
        if(selectedCvApplicationSectionList.size()==0){
            //adding custom error message here
//            bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList","At least One CV Section should be selected "));
            errorMessages.put("cvApplicationSectionList","At least One CV Section should be selected ");

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
