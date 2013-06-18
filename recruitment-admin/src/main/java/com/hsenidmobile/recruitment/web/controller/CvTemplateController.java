package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class CvTemplateController {

    private static final Logger  logger = LoggerFactory.getLogger(CvTemplateController.class);

    @RequestMapping(value = "/cv_section/registration_view",method = RequestMethod.GET)
    public ModelAndView cvTemplateSectionsRegisterView(ModelAndView modelAndView){
        logger.info(" request to display cv template section registration view ");
        modelAndView.setViewName("cv-template/cv-section-register");
        modelAndView.addObject(new CvApplicationSection());
        return modelAndView;
    }

    @RequestMapping(value = "/cv_section/register",method = RequestMethod.POST)
    public ModelAndView registerNewCvTemplateSection(@Valid CvApplicationSection cvApplicationSection,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section");
        modelAndView.setViewName("cv-template/cv-section-register");
        if(bindingResult.hasErrors()){
            System.out.println(" form contains errors");
        }
        else
        {
            System.out.println(" there are no errors");
        }
        return modelAndView;
    }


}
