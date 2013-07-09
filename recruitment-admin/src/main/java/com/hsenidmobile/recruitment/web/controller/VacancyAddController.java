package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/9/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/vacancy_add")
public class VacancyAddController {


    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    

    // @Secured("ROLE_USER")
    @RequestMapping(value = "/vacancy_add_generate_view")
    public ModelAndView showVacancyGeneration(){
        List<CvApplicationTemplate> cvApplicationTemplate = cvApplicationTemplateService.findAllCvTemplate();
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv template ["+cvApplicationTemplate+"]");
        if (cvApplicationTemplate!=null){
            modelAndView.setViewName("vacancy_add_generation/vacancy_add");
            modelAndView.addObject("cvApplicationTemplate",cvApplicationTemplate);
        }
        else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }



    /*
    @RequestMapping(value = "/vacancy_add_generate_view")
    public ModelAndView addPersonFromForm(@Valid Vacancy fileData,
                                          BindingResult bindingResult,
                                          @RequestParam(value = "image", required = false) MultipartFile image) {
        ModelAndView modelAndView = new ModelAndView();

        if (!image.isEmpty()) {
            try {
                validateImage(image);

            } catch (RuntimeException re) {
                bindingResult.reject(re.getMessage());

                return modelAndView;
            }

            try {
                saveImage(fileData.getFilename() + ".jpg", image);
            } catch (IOException e) {
                bindingResult.reject(e.getMessage());
                return modelAndView;


            }
        }


    }
} */
}

