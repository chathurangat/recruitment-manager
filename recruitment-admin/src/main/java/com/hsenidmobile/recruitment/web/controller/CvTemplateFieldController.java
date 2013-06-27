package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.CvApplicationFieldService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view")
    public ModelAndView  cvTemplateFieldRegistrationView(){
        logger.info(" cv template field registration view ");

        Map<String,Object> modelsObjects = new HashMap<String, Object>();
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById("51cc651544ae22da41903b25");

        List<ApplicationFieldDictionary> masterApplicationFieldDictionaryList = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();

        ModelAndView modelAndView = new ModelAndView();
        modelsObjects.put("cvApplicationTemplate",cvApplicationTemplate);
        logger.info(" size of [{}]",masterApplicationFieldDictionaryList.size());
        modelsObjects.put("masterApplicationFieldDictionaryList",masterApplicationFieldDictionaryList);
        modelAndView.setViewName("cv-template/cv-template-field-register");
        modelAndView.addAllObjects(modelsObjects);
        return modelAndView;
    }

}
