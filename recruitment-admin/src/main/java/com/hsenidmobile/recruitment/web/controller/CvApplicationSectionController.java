package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
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

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/cv_section")
public class CvApplicationSectionController {

    private static final Logger logger = LoggerFactory.getLogger(CvApplicationSectionController.class);

    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view",method = RequestMethod.GET)
    public ModelAndView cvTemplateSectionsRegisterView(ModelAndView modelAndView){
        logger.info(" request to display cv template section registration view ");
        modelAndView.setViewName("cv-template/cv-section-register");
        modelAndView.addObject(new CvApplicationSection());
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerNewCvTemplateSection(@Valid CvApplicationSection cvApplicationSection,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section");
        modelAndView.setViewName("cv-template/cv-section-register");
        if(StringUtils.hasText(cvApplicationSection.getId())){
            cvApplicationSectionService.update(cvApplicationSection);
            System.out.println(" form contains errors");
        }
        else
        {
            cvApplicationSectionService.create(cvApplicationSection);
            System.out.println(" there are no errors");
        }
        return modelAndView;
    }
}
