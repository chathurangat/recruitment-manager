package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationField;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.service.CvApplicationFieldService;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class CvFieldController {

    private static final Logger logger = LoggerFactory.getLogger(CvFieldController.class);

    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;

   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_field/registration_view",method = RequestMethod.GET)
    public ModelAndView cvSectionFieldDictionaryRegisterView(ModelAndView modelAndView){
        logger.info(" display cv template section field dictionary registration view ");
        modelAndView.setViewName("cv-template/cv-field-register");
        modelAndView.addObject(new ApplicationFieldDictionary());
        return modelAndView;
    }

   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_field/register",method = RequestMethod.POST)
    public ModelAndView registerNewCvSectionFieldDictionary(@Valid ApplicationFieldDictionary applicationFieldDictionary,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section field dictionary");
        modelAndView.setViewName("cv-template/cv-field-register");
        if(StringUtils.hasText(applicationFieldDictionary.getId())){
            cvApplicationFieldDictionaryService.updateCvSectionFieldDictionary(applicationFieldDictionary);
        }
        else
        {
            cvApplicationFieldDictionaryService.createCvSectionFieldDictionary(applicationFieldDictionary);
        }
        return modelAndView;
    }

}
