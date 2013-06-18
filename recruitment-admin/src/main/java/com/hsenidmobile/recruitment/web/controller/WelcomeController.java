package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    /**
     * <p>
     *     display the user home page of the recruitment web application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_USER
     * </p>
     * @return "welcome" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = {"/home","welcome"},method = RequestMethod.GET)
    public ModelAndView displayWelcomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    /**
     * <p>
     *     this is to check whether the spring security works as expected....
     *     this method is only accessible for the admin users
     * </p>
     * @return "admin_page" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView displayAdminPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_page");
        return modelAndView;
    }

    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/apply")
    public ModelAndView generateCV(){
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById("51c03dc3e4b07255b5d8d4d6");
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv template ["+cvApplicationTemplate+"]");
        if (cvApplicationTemplate!=null){
            modelAndView.setViewName("cv_generation");
            modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;

    @Secured("ROLE_ADMIN")
         @RequestMapping(value = "/register")
         public ModelAndView generateCVSection(){
         CvApplicationSection cvApplicationSection=cvApplicationSectionService.findCvSectionById("51c04d81e4b087d1087d04f6");
        //List<CvApplicationSection> cvApplicationSection = cvApplicationSectionService.findAllCvSection();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv section ["+cvApplicationSection+"]");
        if (cvApplicationSection!=null){
            modelAndView.setViewName("cv_registration");
            modelAndView.addObject("cvApplicationSection",cvApplicationSection);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/register/save",method = RequestMethod.POST)
    public ModelAndView saveCVSection(@ModelAttribute CvApplicationTemplate cvApplicationTemplate, ModelMap model){
           ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("admin_page");
            if(StringUtils.hasText(cvApplicationTemplate.getId()))
            {
                cvApplicationTemplateService.update(cvApplicationTemplate);
            }
            else
            {
               cvApplicationTemplateService.create(cvApplicationTemplate);
            }
        return modelAndView;
        }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public ModelAndView displayInsertAdminPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cv_section_insert");
        return modelAndView;
    }

    @RequestMapping(value = "/insert/save", method = RequestMethod.POST)
    public void insertCvSection(CvApplicationSection cvApplicationSection) {
        if(StringUtils.hasText(cvApplicationSection.getId())) {
            cvApplicationSectionService. update(cvApplicationSection);
        }
        else {
            cvApplicationSectionService.create(cvApplicationSection);
        }
    }


}
