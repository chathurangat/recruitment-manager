package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/insert")
public class CvApplicationSectionController {
    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;

    /*
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String getCvApplicationFieldList(ModelMap model) {
        model.addAttribute("cvApplicationFieldList", cvApplicationSectionService.findAllCvSection());
        return "cv_section_insert";

    }

    @RequestMapping(value = "/insert/save", method = RequestMethod.POST)
    public void insertCvSection(@ModelAttribute CvApplicationSection cvApplicationSection, ModelMap model) {
        if(StringUtils.hasText(cvApplicationSection.getId())) {
            cvApplicationSectionService.update(cvApplicationSection);
             }

        else {
            cvApplicationSectionService.create(cvApplicationSection);
        }

    }     */

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public ModelAndView cvTemplateSectionsInsertView(ModelAndView modelAndView){
        //logger.info(" request to display cv template section registration view ");
        modelAndView.setViewName("cv_section_insert");
        modelAndView.addObject(new CvApplicationSection());
        return modelAndView;
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/insert/save",method = RequestMethod.POST)
    public ModelAndView registerNewCvTemplateSection(@Valid CvApplicationSection cvApplicationSection,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section");
        modelAndView.setViewName("cv_section_insert");
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
