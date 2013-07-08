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

    /**
     * <p>
     *     display the Cv Section Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "cv-section-register" logical name encapsulated in {@link ModelAndView}
     */
   @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/registration_view",method = RequestMethod.GET)
    public ModelAndView cvTemplateSectionsRegisterView(ModelAndView modelAndView){
        logger.info(" request to display cv template section registration view ");
        modelAndView.setViewName("cv-template/cv-section-register");
        modelAndView.addObject(new CvApplicationSection());
        return modelAndView;
    }


    /**
     * <p>
     *     Insert the Cv Section items into CvApplicationSection collections
     *     Then display the Cv Section Registration page of the recruitment admin application
     *     this method will only support for HTTP GET requests
     *     the access will be granted for the authenticated users with ROLE_ADMIN
     * </p>
     * @return "cv-section-register" logical name encapsulated in {@link ModelAndView}
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerNewCvTemplateSection(@Valid CvApplicationSection cvApplicationSection,BindingResult bindingResult,ModelAndView modelAndView){
        System.out.println(" registering new cv template section");
        modelAndView.setViewName("cv-template/cv-section-register");
        if(!bindingResult.hasErrors()){
            if(StringUtils.hasText(cvApplicationSection.getId())){
                cvApplicationSectionService.update(cvApplicationSection);
                logger.info("registering new cv template section form contains no errors (update) ");
                System.out.println(" there are no errors (update)");
                modelAndView.setViewName("cv-template/cv-section-register-success");
            }
            else
            {
                cvApplicationSectionService.create(cvApplicationSection);
                logger.info("registering new cv template section form contains no errors (create) ");
                System.out.println(" there are no errors (create)");
                modelAndView.setViewName("cv-template/cv-section-register-success");

            }
            return modelAndView;
        }
        else {

            logger.info("registering new cv template section form contains errors ");
           // modelAndView.setViewName("cv-template/cv-section-register-failed");

            return modelAndView;
        }

    }
}
