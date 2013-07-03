package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.model.*;
import com.hsenidmobile.recruitment.service.ApplicantService;
import com.hsenidmobile.recruitment.service.CvApplicationService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/applicant/")
public class ApplicantController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantController.class);

    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private CvApplicationService cvApplicationService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/apply")
    public ModelAndView generateCV(@RequestParam("id")String cvTemplateId){
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById(cvTemplateId);
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

    /**
     * <p>
     *     submit application for a vacancy
     * </p>
     * @return
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/application_save")
    public ModelAndView submitCvApplication(HttpServletRequest request){
        String cvTemplateId = request.getParameter("id");
        logger.info(" user submitted the cv application with cvTemplateId [{}]",cvTemplateId);
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById(cvTemplateId);
        //setting up the data submitted trough the form
        if(cvApplicationTemplate!=null){
            //getting the sections list of the application template
            List<CvApplicationSection> cvApplicationSectionList =  cvApplicationTemplate.getCvApplicationSectionList();
            if(cvApplicationSectionList!=null){
                //iterating though each section
                for(CvApplicationSection cvApplicationSection:cvApplicationSectionList){
                    if(cvApplicationSection!=null){
                        //getting the application field list assigned under the given section
                        List<CvApplicationField> applicationFieldList = cvApplicationSection.getCvApplicationFieldList();
                        if(applicationFieldList!=null){
                            for(CvApplicationField cvApplicationField:applicationFieldList){
                                if(cvApplicationField.getId()!=null){
                                    logger.info(" getting the value of the CV application field [{}] ",cvApplicationField.getId());
                                    //getting the submitted value from the request
                                    String paramValue = request.getParameter(cvApplicationField.getId());
                                    logger.info("ParamValue"+paramValue);
                                    cvApplicationField.setFieldValue(paramValue);
                                }
                            }
                        }
                    }
                }
            }
        }
        logger.info("start to get Session");
        //todo move session key to common key class
        //saving the application submitted
        String userId = request.getSession().getAttribute("user-id").toString();
        logger.info("UserId"+userId);
        Applicant applicant = applicantService.findApplicantById(userId);
     //   Applicant applicant = new Applicant();
     //   applicant.setApplicantName("Nilaxan");
        logger.info("Applicant"+applicant);
        //create CV application
        CvApplication cvApplication = new CvApplication();
        //setting cv application name
        cvApplication.setApplicationName(cvApplicationTemplate.getCvHeaderEn());
        //adding current date and time
        Date date = new Date();
        cvApplication.setDateApplied(date);

        cvApplication.setCvApplicationTemplate(cvApplicationTemplate);
        applicant.submitApplication(cvApplication);
        String applicationId = cvApplicationService.create(cvApplication);
        logger.info("ApplicationId"+applicationId);
        ModelAndView modelAndView = new ModelAndView();
        if(applicationId!=null){
            modelAndView.setViewName("redirect:application-save-success?id="+applicationId);
            logger.info(" new application was saved successfully with application id [{}]", applicationId);
        }
        else{
            modelAndView.setViewName("cv_generation");
            modelAndView.addObject("error",true);
            logger.info(" there was an error encountered with saving the application ");
        }

        return modelAndView;
    }



    @Secured("ROLE_USER")
    @RequestMapping(value = "/application-save-success")
    public ModelAndView displayApplicationSaveSuccessPage(@RequestParam("id")String cvTemplateId){
        logger.info(" displaying the application success page after application was saved successfully");
        CvApplication cvApplication = cvApplicationService.findById(cvTemplateId);
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(" application cv template ["+cvApplication+"]");
        if (cvApplication!=null){
            modelAndView.setViewName("application-save-success");
            modelAndView.addObject("cvApplication", cvApplication);
        }

        return modelAndView;
    }
}
