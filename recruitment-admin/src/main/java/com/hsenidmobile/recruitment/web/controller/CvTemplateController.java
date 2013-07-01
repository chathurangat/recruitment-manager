package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

/**
 * <P>
 *     this controller will contain the method to perform the administrative operations related to the
 *     cv template such as displaying cv template registration view, creating new cv template, updating cv template.
 *     all the cv template related operations(method) should be added here
 * </P>
 */
@Controller
@RequestMapping(value = "/cv_template")
public class CvTemplateController {

    private static final Logger  logger = LoggerFactory.getLogger(CvTemplateController.class);

    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;
    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private MessageSource messageSource;

    /**
     * <p>
     *     displaying the cv template registration view with cv sections
     * </p>
     * @return an instance of {@link ModelAndView} containing the logical view name for the Cv Template registration view
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view",method = RequestMethod.GET)
    public ModelAndView CvTemplateRegisterView(){
        ModelAndView modelAndView =  new ModelAndView();
        logger.info(" cv template registration view ");
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

    /**
     * <p>
     *  this method will accept the cvTemplate instance and proceed with creating new or updating existing cv template.
     *  the create or update operation will be automatically determined based on the cvTemplate object status
     * </p>
     * @param cvApplicationTemplate  encapsulates the cv template data submitted by user as {@link CvApplicationTemplate}
     * @param bindingResult as {@link BindingResult} supports to do the jsr 303 validation
     * @param modelAndView  as {@link ModelAndView}
     * @param session  as {@link HttpSession} and if new Cv Template was created or updated, session will be  updated with the relevant ID of the CV Template
     * @return  an instance of {@link ModelAndView} that encapsulates the logical view name of the user screen based on the operation status
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerOrUpdateCvTemplate(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,ModelAndView modelAndView,HttpSession session){
        List<CvApplicationSection> selectedCvApplicationSectionList = new ArrayList<CvApplicationSection>();
        Map<String,String> errorMessages = new HashMap<String,String>();
        //getting the current user locale
        Locale currentUserLocale = this.getUserLocale(session);
        this.findSelectedCvApplicationListForCvTemplate(cvApplicationTemplate, selectedCvApplicationSectionList, errorMessages);
        logger.info(" user has selected [{}] cv sections for this template out of [{}] sections", selectedCvApplicationSectionList.size(), cvApplicationTemplate.getCvApplicationSectionList().size());
        if(errorMessages.size()>0){
            logger.info(" [{}] number of errors found with the submitted form",errorMessages.size());
            //binding each error message to the relevant field
            for(Map.Entry<String,String> entry:errorMessages.entrySet()){
                String message = messageSource.getMessage(entry.getValue(),null,currentUserLocale);
                bindingResult.addError(new FieldError("cvApplicationTemplate",entry.getKey(),message));
            }
        }
        //creating or updating the CV Template
        if(!bindingResult.hasErrors()){
            logger.info(" submitted form contains valid data ");
            cvApplicationTemplate.setCvApplicationSectionList(selectedCvApplicationSectionList);
            modelAndView = createOrUpdateCvTemplate(cvApplicationTemplate);
            if(cvApplicationTemplate.getId()!=null){
                session.setAttribute("last-created-cv-template-id", cvApplicationTemplate.getId());
            }
        }
        else{
            logger.info(" submitted form contains field errors ");
            //loading the UI again
            modelAndView = this.initializeCvTemplateRegistrationView();
        }
        return modelAndView;
    }

    /**
     * <p>
     *  displaying the success notification page for the created or updated CV template
     * </p>
     * @param session as {@link HttpSession} and will contains the ID of the CV Template that was created or updated lastly
     * @return an instance of {@link ModelAndView} that encapsulates the logical view name for the cv template registration success page and relevant {@link CvApplicationTemplate} instance
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv-template-register-success",method = RequestMethod.GET)
    public ModelAndView cvApplicationTemplateRegistrationSuccess(HttpSession session){
        String lastCreatedCvTemplateId = (String) session.getAttribute("last-created-cv-template-id");
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById(lastCreatedCvTemplateId);
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.addObject("cvApplicationTemplate", cvApplicationTemplate);
        modelAndView.setViewName("cv-template/cv-template-register-success");
        return modelAndView;
    }

    /**
     * <p>
     *     this method will create or update the given cvTemplate instance based on the object status
     * </p>
     * @param cvApplicationTemplate instance to be created or updated as as {@link CvApplicationTemplate}
     * @return  an instance of {@link ModelAndView} that encapsulates the logical view name of the user screen based on the operation status
     */
    private ModelAndView createOrUpdateCvTemplate(CvApplicationTemplate cvApplicationTemplate){
        ModelAndView modelAndView = new ModelAndView();
        if(StringUtils.hasText(cvApplicationTemplate.getId()))
        {
            cvApplicationTemplateService.update(cvApplicationTemplate);
            logger.info("updated the cv application template");
            modelAndView.setViewName("redirect:cv-template-register-success");
        }
        else
        {
            cvApplicationTemplateService.create(cvApplicationTemplate);
            if(cvApplicationTemplate.getId()==null || (cvApplicationTemplate.getId()!=null && cvApplicationTemplate.getId().trim().equals(""))){
                logger.info(" new cv template was not created successfully ");
                modelAndView = this.initializeCvTemplateRegistrationView();
                //initializing the error model to say that error has occurred
                modelAndView.addObject("error",true);
            }
            else{
                modelAndView.setViewName("redirect:cv-template-register-success");
                logger.info("created new cv application template");
            }
        }
        return modelAndView;
    }

    /**
     * <p>
     *     this will initialize required model objects to display the cv template registration view
     * </p>
     * @return an instance of {@link ModelAndView} that encapsulates the logical view name for the  cv template registration view and initial model data to load the UI
     */
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
     * @param cvApplicationTemplate will be the model object that encapsulates all the submitted cv template data by the user
     * @param selectedCvApplicationSectionList will be modified inside the method to hold the list of {@link CvApplicationSection} instances selected by the user for the CvTemplate being created
     * @param errorMessages  will be modified inside the method to hold the error messages related to the cv sections and their priories
     */
    private void findSelectedCvApplicationListForCvTemplate(CvApplicationTemplate cvApplicationTemplate,List<CvApplicationSection> selectedCvApplicationSectionList,Map<String,String> errorMessages){
        //this map will hold a set of entered priorities for each cv application section
        Map<Integer,Integer> enteredPriorities = new HashMap<Integer, Integer>();
        for(int index=0;index<cvApplicationTemplate.getCvApplicationSectionList().size();index++){
            CvApplicationSection cvApplicationSection = cvApplicationTemplate.getCvApplicationSectionList().get(index);
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
        }
        //check whether user has selected at least one cv section fr the cv template
        if(selectedCvApplicationSectionList.size()==0){
            //adding custom error message here
            errorMessages.put("cvApplicationSectionList","error.cv_template_registration.cv.section.should.be.selected");
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
                errorMessages.put("cvApplicationSectionList["+cvSectionIndex+"].priority","error.cv.template.registration.duplicate.priority");
                //error message for the the already inserted duplicate priority
                errorMessages.put("cvApplicationSectionList["+priorityMap.get(cvSectionPriority)+"].priority","error.cv.template.registration.duplicate.priority");
            }
        }
        else{
            errorMessages.put("cvApplicationSectionList["+cvSectionIndex+"].priority","error.cv.template.registration.priority.required");
        }
    }


    /**
     * <p>
     *     getting the user locale from the user session
     * </p>
     * @param session as {@link HttpSession}
     * @return current user locale as {@link Locale}
     */
    private Locale getUserLocale(HttpSession session){
        if(session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME)!=null){
            return (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        }
        else{
            //setting up the default locale to english
            return new Locale("en");
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
