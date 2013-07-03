package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.CvApplication;
import com.hsenidmobile.recruitment.service.CvApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/cv_application")
public class CvApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(CvApplicationController.class);

    @Autowired
    private CvApplicationService cvApplicationService;

    /**
     * <p>
     *     display all applicant applied for vacancies
     * </p>
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/applied-applicant")
    public ModelAndView displayAppliedApplicantPage(){
        logger.info(" displaying the applied application name details");
        List<CvApplication> cvApplication = cvApplicationService.findAllCvApplications();
        ModelAndView modelAndView = new ModelAndView();
        if (cvApplication!=null){
            modelAndView.setViewName("cv-application/applied-applicant");
            modelAndView.addObject("cvApplication", cvApplication);
        }

        return modelAndView;
    }

    /**
     * <p>
     *     display all applicant applied for vacancies
     * </p>
     * @return
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/display-application")
    public ModelAndView displayApplicationPage(@RequestParam("id")String cvTemplateId){
        logger.info(" displaying the selected applied applicant's application details");
        CvApplication cvApplication = cvApplicationService.findById(cvTemplateId);
        ModelAndView modelAndView = new ModelAndView();
        if (cvApplication!=null){
            modelAndView.setViewName("cv-application/display-application");
            modelAndView.addObject("cvApplication", cvApplication);
        }

        return modelAndView;
    }
}
