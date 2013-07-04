package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationDao;
import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.model.CvApplication;
import com.hsenidmobile.recruitment.model.EmailMessage;
import com.hsenidmobile.recruitment.model.EmailTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cvApplicationService")
public class CvApplicationServiceImpl implements CvApplicationService{

    @Autowired
    private MailSender mailSender;

    @Autowired
    private CvApplicationDao cvApplicationDao;

    @Transactional
    public String create(CvApplication cvApplication) {
        //creating CV Application record
       String persistedId = cvApplicationDao.create(cvApplication);
        if(persistedId!=null){
            //sending the email for user
            EmailTemplate emailTemplate = new EmailTemplate();
            emailTemplate.setSubject("Thank you for Applying the Post for Software Engineer");
            emailTemplate.setBody("Your application has been successfully received");

            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setToAddress("chathuranga.t@gmail.com");
            emailMessage.setReplyTo("abanstest@gmail.com");
            emailMessage.setFrom("abanstest@gmail.com");
            emailMessage.setEmailTemplate(emailTemplate);
//
//            emailService.sendEmail(emailMessage);
            Thread thread = new EmailService(emailMessage,mailSender);
            thread.start();

        }
      return persistedId;
    }

    /**
     * <p>
     *     remove the given cv application section
     * </p>
     * @param cvApplication as {@link CvApplication}
     */
    @Override
    public void remove(CvApplication cvApplication) {
        cvApplicationDao.remove(cvApplication);
    }


    /**
     * <p>
     *     find cv section with given id
     * </p>
     * @param applicationId as {@link String}
     * @return instance of {@link CvApplication}
     */
    @Override
    public CvApplication findById(String applicationId){
        return cvApplicationDao.findById(applicationId);
    }

    /**
     * <p>
     *     find all cv applications by applicant id
     * </p>
     *
     */
    @Override
    public List<CvApplication> findCvApplicationsByApplicant(Applicant applicant){
        return cvApplicationDao.findCvApplicationsByApplicant(applicant);
    }

    /**
     * <p>
     *     find all cv applications
     * </p>
     *
     */
    @Override
    public List<CvApplication> findAllCvApplications(){
        return cvApplicationDao.findAllCvApplications();
    }

}
