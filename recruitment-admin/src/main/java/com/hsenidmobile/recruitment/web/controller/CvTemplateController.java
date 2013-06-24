package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
<<<<<<< HEAD
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
>>>>>>> 85f0d879e263a7905ffbd79b29c2797f44b3156f
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

<<<<<<< HEAD
    //@Secured("ROLE_ADMIN")
=======
    @Secured("ROLE_ADMIN")
>>>>>>> 85f0d879e263a7905ffbd79b29c2797f44b3156f
    @RequestMapping(value = "/cv_section/registration_view",method = RequestMethod.GET)
    public ModelAndView cvTemplateSectionsRegisterView(ModelAndView modelAndView){
        logger.info(" request to display cv template section registration view ");
        modelAndView.setViewName("cv-template/cv-section-register");
        modelAndView.addObject(new CvApplicationSection());
        return modelAndView;
    }

   // @Secured("ROLE_ADMIN")
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


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_template/registration_view",method = RequestMethod.GET)
    public ModelAndView CvTemplateRegisterView(){
        //CvApplicationSection cvApplicationSection=cvApplicationSectionService.findCvSectionById("51c153bae4b005665847d347");
        List<CvApplicationSection> cvApplicationSection = cvApplicationSectionService.findAllCvSection();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv section ["+cvApplicationSection+"]");
        if (cvApplicationSection!=null){
            modelAndView.setViewName("cv-template/cv_template_register");
            modelAndView.addObject("cvApplicationSection",cvApplicationSection);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/cv_template/register",method = RequestMethod.POST)
    public View registerNewCvTemplate(@Valid CvApplicationTemplate cvApplicationTemplate,BindingResult bindingResult,ModelAndView modelAndView){

        //modelAndView.setViewName("cv-template/cv_registration");
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
