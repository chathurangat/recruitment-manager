package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.CvApplicationFieldService;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cv_template_field")
public class CvTemplateFieldController {

    private static final Logger logger = LoggerFactory.getLogger(CvTemplateFieldController.class);

    @Autowired
    private CvApplicationFieldService cvApplicationFieldService;
    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;
    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;

    //    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view")
    public ModelAndView  cvTemplateFieldRegistrationView(){
        logger.info(" cv template field registration view ");

        Map<String,Object> modelsObjects = new HashMap<String, Object>();
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById("51cc651544ae22da41903b25");

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


    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public ModelAndView  cvTemplateFieldRegistration(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult){
        logger.info(" cv template field registration process start for template  [{}]",cvApplicationTemplate.getCvHeaderEn());

        List<CvApplicationSection> cvApplicationSectionList = cvApplicationTemplate.getCvApplicationSectionList();
        //getting the submitted cv field list for each sections
        for(int sectionIndex=0;sectionIndex<cvApplicationSectionList.size();sectionIndex++){
            //getting the current cv application section
            CvApplicationSection cvApplicationSection = cvApplicationTemplate.getCvApplicationSectionList().get(sectionIndex);

            logger.info(" getting submitted fields of the Cv Application Section  [{}]",cvApplicationSection.getSectionNameEn());

            //this will hold the submitted application field list
            List<CvApplicationField> submittedFieldList =  new ArrayList<CvApplicationField>();
            //this will hold the map of priority submitted for the given cv application section
            Map<Integer,Integer>  prioritySubmitted = new HashMap<Integer, Integer>();
            //this will hold the duplicate priority indexes
//            List<Integer> duplicatePriorityIndexes = new ArrayList<Integer>();

            //getting the list of cv application fields submitted
            for(int fieldIndex=0;fieldIndex<cvApplicationSection.getCvApplicationFieldList().size();fieldIndex++){
                //getting the current application field
                CvApplicationField applicationField = cvApplicationSection.getCvApplicationFieldList().get(fieldIndex);
                if(applicationField.getApplicationFieldDictionary().getId()!=null){
                    logger.info(" user has selected cv field dictionary item id [{}]",applicationField.getApplicationFieldDictionary().getId());
                    //getting the up to date field dictionary instance
                    String currentId = applicationField.getApplicationFieldDictionary().getId();
                    ApplicationFieldDictionary applicationFieldDictionary = cvApplicationFieldDictionaryService.findCvSectionFieldDictionaryById(currentId);
                    applicationField.setApplicationFieldDictionary(applicationFieldDictionary);
                    //checking whether th user has selected the priority for the selected field
                    if(applicationField.getPriority()==-1){
                     bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList["+fieldIndex+"].priority","Priority is required "));
                    }
                    else{
                        //checking for duplicate priorities
                        if(!prioritySubmitted.containsKey(applicationField.getPriority())){
                            //field priority within the section has not ben duplicated so far
                            logger.info(" putting priority [{}]",applicationField.getPriority());
                            prioritySubmitted.put(applicationField.getPriority(),fieldIndex);
                        }
                        else{
                            //field priority within the section has been duplicated. therefore putting an error message
                            //adding the current duplicate record
//                            duplicatePriorityIndexes.add(fieldIndex);
                            logger.info(" duplicate priority message");
                            bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList["+fieldIndex+"].priority","Duplicate priority detected "));
                            //adding index the previous duplicate record matches with thee current priority
//                            duplicatePriorityIndexes.add(prioritySubmitted.get(applicationField.getPriority()));
                            bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList["+prioritySubmitted.get(applicationField.getPriority())+"].priority","Duplicate Priority detected "));

                        }
                    }
                    //adding the cv application field for submitted field list
                    submittedFieldList.add(applicationField);
                }
            }
            //now checking whether user has selected at least one cv field for the current section
            if(submittedFieldList.size()==0){
               logger.info(" at least one cv field should be selected for Cv Section [{}]",cvApplicationSection.getSectionNameEn());
//                bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList[0].priority","At least one field should be selected "));
                bindingResult.addError(new FieldError("cvApplicationTemplate","cvApplicationSectionList["+sectionIndex+"].id","At least one field should be selected for the section "));
                logger.info("cvApplicationSectionList["+sectionIndex+"].cvApplicationFieldList[0].applicationFieldDictionary.id");
            }
            //setting up the submitted cv application field list to cv application section
//            cvApplicationSection.setCvApplicationFieldList(submittedFieldList);
        }

        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            logger.info(" form contains errors");
        Map<String,Object> modelsObjects = new HashMap<String, Object>();

        List<ApplicationFieldDictionary> masterApplicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        List<Integer> priorityList = this.createPriorityLit(masterApplicationFieldDictionaryList);

        modelsObjects.put("cvApplicationTemplate",cvApplicationTemplate);
        modelsObjects.put("priorityList", priorityList);
        modelsObjects.put("masterApplicationFieldDictionaryList", masterApplicationFieldDictionaryList);
        modelAndView.addAllObjects(modelsObjects);
        }
        modelAndView.setViewName("cv-template/cv-template-field-register");
//        }
        return modelAndView;
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
}
