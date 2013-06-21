package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CvApplicationSectionController {
    @Autowired
    private CvApplicationSectionService cvApplicationSectionService;

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

    }

}
