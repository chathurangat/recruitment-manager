package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/cv_template_field")
public class CvTemplateFieldController {

    private static final Logger logger = LoggerFactory.getLogger(CvTemplateFieldController.class);
    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;
    @Autowired
    private MessageSource messageSource;
    /**
     * <p>
     *     displaying the required view to assign/update cv application field for the given Cv Application Template
     * </p>
     *
     * @return an instance of {@link ModelAndView} containing the logical view name for the Cv Template field registration view
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/display-cv-template")
    public ModelAndView displayCvTemplate(){
        List<CvApplicationTemplate> cvApplicationTemplate = cvApplicationTemplateService.findAllCvTemplate();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv template ["+cvApplicationTemplate+"]");
        if (cvApplicationTemplate!=null){
            modelAndView.setViewName("cv-template/cv-template-display");
            modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    /**
     * <p>
     *     displaying the required view to assign/update cv application field for the given Cv Application Template
     * </p>
     * @param cvTemplateId will be the id of the cv application template
     * @return an instance of {@link ModelAndView} containing the logical view name for the Cv Template field registration view
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view")
    public ModelAndView  cvTemplateFieldRegistrationView(@RequestParam("id")String cvTemplateId){
        logger.info(" cv template field registration view for cv Template with id [{}]",cvTemplateId);
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById(cvTemplateId);
        if(cvApplicationTemplate!=null){
            return this.initializeCvTemplateFieldRegistrationView(cvApplicationTemplate);
        }
        else{
            //given cv template id does not associate with cv template instance
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public ModelAndView  cvTemplateFieldRegistration(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,HttpSession session){
        logger.info(" cv field(s) registration/update request for cv template  [{}]",cvApplicationTemplate.getCvHeaderEn());
        Map<String,String> errorMessages =  this.validateCvTemplate(cvApplicationTemplate);
        ModelAndView modelAndView = new ModelAndView();
        if(errorMessages!=null && errorMessages.size()!=0){
            Locale userLocale = this.getUserLocale(session);
            for(Map.Entry<String,String> entry: errorMessages.entrySet()){
                logger.info(" error key [{}] and value [{}]",entry.getKey(),entry.getValue());
                //localizing the error messages and binding them to fields
                bindingResult.addError(new FieldError("cvApplicationTemplate",entry.getKey(),messageSource.getMessage(entry.getValue(),null,userLocale)));
            }
        }
        if(bindingResult.hasErrors()){
            logger.info(" submitted form contains errors");
            modelAndView = this.initializeCvTemplateFieldRegistrationView(cvApplicationTemplate);
        }
        else{
            cvApplicationTemplateService.update(cvApplicationTemplate);
            modelAndView.setViewName("redirect:cv-template-field-registration-success");
            session.setAttribute("last-created-cv-template-id",cvApplicationTemplate.getId());
        }
        return modelAndView;
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv-template-field-registration-success")
    public ModelAndView displayCvTemplateFieldRegistrationSuccessPage(HttpSession session){
        ModelAndView modelAndView =  new ModelAndView();
        String cvApplicationTemplateId = (String) session.getAttribute("last-created-cv-template-id");
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById(cvApplicationTemplateId);
        modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
        modelAndView.setViewName("cv-template/cv-template-field-registration-success");
        return modelAndView;
    }


    /**
     * <p>
     *     initializing the required data and model to load the UI view to register fields for the given cv template
     * </p>
     * @param cvApplicationTemplate as {@link CvApplicationTemplate}
     * @return an instance of {@link ModelAndView} containing the logical view name for the Cv Template field registration view with required initializing data
     */
    private ModelAndView initializeCvTemplateFieldRegistrationView(CvApplicationTemplate cvApplicationTemplate){
        logger.info(" initializing the required data and model to load cv template field registration view");
        Map<String,Object> modelsObjects = new HashMap<String, Object>();
        List<ApplicationFieldDictionary> masterApplicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<Integer> priorityList = this.createPriorityLit(masterApplicationFieldDictionaryList);
        ModelAndView modelAndView = new ModelAndView();
        modelsObjects.put("cvApplicationTemplate",cvApplicationTemplate);
        modelsObjects.put("priorityList", priorityList);
        modelsObjects.put("masterApplicationFieldDictionaryList", masterApplicationFieldDictionaryList);
        modelAndView.setViewName("cv-template/cv-template-field-register");
        modelAndView.addAllObjects(modelsObjects);
        return modelAndView;
    }


    private Map<String,String>  validateCvTemplate(CvApplicationTemplate cvApplicationTemplate){
        Map<String,String> errorMessages = new HashMap<String, String>();
        List<CvApplicationSection> cvApplicationSectionList = cvApplicationTemplate.getCvApplicationSectionList();
        //getting the submitted cv field list for each sections
        for(int sectionIndex=0;sectionIndex<cvApplicationSectionList.size();sectionIndex++){
            //getting the current cv application section
            CvApplicationSection cvApplicationSection = cvApplicationTemplate.getCvApplicationSectionList().get(sectionIndex);
            logger.info(" getting submitted fields of the Cv Application Section  [{}]",cvApplicationSection.getSectionNameEn());
            this.validateCvSectionFields(cvApplicationSection,sectionIndex,errorMessages);
        }
        return errorMessages;
    }


    private void validateCvSectionFields(CvApplicationSection cvApplicationSection,int sectionIndex,Map<String,String> errorMessages){
        //this will hold the submitted application field list
        List<CvApplicationField> submittedFieldList =  new ArrayList<CvApplicationField>();
        for(CvApplicationField cvApplicationField:cvApplicationSection.getCvApplicationFieldList()){
            if(cvApplicationField.getId()!=null){
                submittedFieldList.add(cvApplicationField);
            }
        }
        //checking the size of the submitted cv application field list
        if(submittedFieldList.size()==0){
            logger.info(" at least one cv field should be selected for Cv Section [{}]", cvApplicationSection.getSectionNameEn());
            errorMessages.put("cvApplicationSectionList[" + sectionIndex + "].id", "error.cv_template_field_registration.one_field.is.required");
        }
        else {
            //validating the priority of the submitted field list
            this.validatePriorityOfCvSectionFieldList(submittedFieldList,sectionIndex,errorMessages);
        }
    }


    private void validatePriorityOfCvSectionFieldList(List<CvApplicationField> cvApplicationFieldList,int sectionIndex,Map<String,String> errorMessages){
        //this will hold the map of priority submitted for the given cv application section
        Map<Integer,Integer>  prioritySubmitted = new HashMap<Integer, Integer>();
        //getting the list of cv application fields submitted
        for(int fieldIndex=0;fieldIndex<cvApplicationFieldList.size();fieldIndex++){
            //getting the current application field
            CvApplicationField applicationField = cvApplicationFieldList.get(fieldIndex);
            if(applicationField.getId()!=null){
                logger.info(" user has selected cv field dictionary item id [{}]", applicationField.getId());
                //getting the up to date field dictionary instance
                ApplicationFieldDictionary applicationFieldDictionary = cvApplicationFieldDictionaryService.findCvSectionFieldDictionaryById(applicationField.getId());
                applicationField.setApplicationFieldDictionary(applicationFieldDictionary);
                //checking whether th user has selected the priority for the selected field
                if(applicationField.getPriority()==-1){
                    errorMessages.put("cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList["+fieldIndex+"].priority","error.cv_template_field_registration.priority.required");
                }
                else{
                    //checking for duplicate priorities
                    if(!prioritySubmitted.containsKey(applicationField.getPriority())){
                        //field priority within the section has not ben duplicated so far
                        prioritySubmitted.put(applicationField.getPriority(),fieldIndex);
                    }
                    else{
                        //field priority within the section has been duplicated. therefore putting an error message
                        //adding the current field duplicate record error  message
                        errorMessages.put("cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList["+fieldIndex+"].priority","error.cv_template_field_registration.duplicate.priority.found");
                        //adding the error message for previous duplicate record matches with the current priority
                        errorMessages.put("cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList["+prioritySubmitted.get(applicationField.getPriority())+"].priority","error.cv_template_field_registration.duplicate.priority.found");
                    }
                }
            }
            else{
                //setting up the priority of the non selected fields to default priority if the user has changed the priority
                applicationField.setPriority(-1);
            }
        }
    }

    /**
     * <p>
     *     creating the priority list based on the applicationFieldDictionaryList
     * </p>
     * @param applicationFieldDictionaryList contains the list of {@link ApplicationFieldDictionary} instances found
     * @return list of priorities based on the size of the cv field dictionary list
     */
    private List<Integer> createPriorityLit(List<ApplicationFieldDictionary> applicationFieldDictionaryList){
        List<Integer> priorityList = new ArrayList<Integer>();
        if(applicationFieldDictionaryList!=null){
            for(int i=1;i<=applicationFieldDictionaryList.size();i++){
                priorityList.add(i);
            }
        }
        return priorityList;
    }


    /**
     * <p>
     *     getting the user locale from the user session
     * </p>
     * @param session as {@link HttpSession}
     * @return current user locale as {@link java.util.Locale}
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
}
