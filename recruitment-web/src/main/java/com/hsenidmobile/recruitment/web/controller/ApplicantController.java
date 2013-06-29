package com.hsenidmobile.recruitment.web.controller;

import com.hsenidmobile.recruitment.dao.CvApplicationDao;
import com.hsenidmobile.recruitment.model.*;
import com.hsenidmobile.recruitment.service.ApplicantService;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Controller
public class ApplicantController {


    @Autowired
    private CvApplicationTemplateService cvApplicationTemplateService;
    @Autowired
    private ApplicantService applicantService;
    //todo migrate to service layer
    @Autowired
    private CvApplicationDao cvApplicationDao;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/apply")
    public ModelAndView generateCV(){
        CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById("51ce78de44ae1f0c38b6be4e");
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
    @RequestMapping(value = "/applicationSubmit")
    public void submitCvApplication(HttpServletRequest request){
        System.out.println(" user submitted the cv application ");
//        Enumeration enumeration = request.getParameterNames();
//        while (enumeration.hasMoreElements()){
//            String paramName = enumeration.nextElement().toString();
//            String paramValue = request.getParameter(paramName);
//            System.out.println(" parameter name ["+paramName+"] and value ["+paramValue+"]");
//        }
       CvApplicationTemplate cvApplicationTemplate = cvApplicationTemplateService.findCvTemplateById("51c71d1244aea6983bfae324");

        //setting up the data submitted trough the form
        if(cvApplicationTemplate!=null){
         List<CvApplicationSection> cvApplicationSectionList =  cvApplicationTemplate.getCvApplicationSectionList();

            if(cvApplicationSectionList!=null){

                for(CvApplicationSection cvApplicationSection:cvApplicationSectionList){

                    if(cvApplicationSection!=null){

                        List<CvApplicationField> applicationFieldList = cvApplicationSection.getCvApplicationFieldList();

                        if(applicationFieldList!=null){

                            for(CvApplicationField cvApplicationField:applicationFieldList){
                                System.out.println(" getting the value of the CV application field ["+cvApplicationField.getId()+"] ");

                                String paramValue = request.getParameter(cvApplicationField.getId());
                                cvApplicationField.setFieldValue(paramValue);
                            }
                        }

                    }
                }

            }
        }

        //todo move session key to common key class
        //saving the application submitted
        String userId = request.getSession().getAttribute("user-id").toString();
       Applicant applicant = applicantService.findApplicantById(userId);

        //create CV application
        CvApplication cvApplication = new CvApplication();

        cvApplication.setCvApplicationTemplate(cvApplicationTemplate);

        applicant.submitApplication(cvApplication);

        cvApplicationDao.create(cvApplication);


    }
}
