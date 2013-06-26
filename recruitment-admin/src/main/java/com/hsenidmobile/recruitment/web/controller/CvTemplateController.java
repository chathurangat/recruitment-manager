package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
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
import java.util.List;

@Controller
@RequestMapping(value = "/register")
public class CvTemplateController {

    private static final Logger  logger = LoggerFactory.getLogger(CvTemplateController.class);

    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_section/registration_view",method = RequestMethod.GET)
    public ModelAndView cvTemplateSectionsRegisterView(ModelAndView modelAndView){
        logger.info(" request to display cv template section registration view ");
        modelAndView.setViewName("cv-template/cv-section-register");
        modelAndView.addObject(new CvApplicationSection());
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_section/register",method = RequestMethod.POST)
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

    @Autowired
    private CvApplicationFieldDictionaryService cvApplicationFieldDictionaryService;

   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_template/registration_view",method = RequestMethod.GET)
    public ModelAndView CvTemplateRegisterView(ModelAndView modelAndView){
        //CvApplicationSection cvApplicationSection=cvApplicationSectionService.findCvSectionById("51c153bae4b005665847d347");
        List<CvApplicationSection> cvApplicationSection = cvApplicationSectionService.findAllCvSection();
        List<ApplicationFieldDictionary> applicationFieldDictionary = cvApplicationFieldDictionaryService.findAllCvSectionFieldDictionary();
        System.out.println(" application cv section ["+cvApplicationSection+"]");
        if (cvApplicationSection!=null){
            modelAndView.setViewName("cv-template/cv-template-register");
            modelAndView.addObject("cvApplicationSection",cvApplicationSection);
            modelAndView.addObject("applicationFieldDictionary",applicationFieldDictionary);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;

   // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_template/register",method = RequestMethod.POST)
    public View registerNewCvTemplate(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,ModelAndView modelAndView){

        if(StringUtils.hasText(cvApplicationTemplate.getId()))
        {
            cvApplicationTemplateService.update(cvApplicationTemplate);
            logger.info("inside update");

        }
        else
        {
            cvApplicationTemplateService.create(cvApplicationTemplate);
            logger.info("inside create");
        }
        return new RedirectView("registration_view");
    }

}
