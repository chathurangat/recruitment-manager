package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationDao;
import com.hsenidmobile.recruitment.model.CvApplication;
import com.hsenidmobile.recruitment.model.EmailMessage;
import com.hsenidmobile.recruitment.model.EmailTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationService;
import com.hsenidmobile.recruitment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cvApplicationService")
public class CvApplicationServiceImpl implements CvApplicationService{

    @Autowired
    private EmailService emailService;

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

            emailService.sendEmail(emailMessage);
        }
      return persistedId;
    }
}