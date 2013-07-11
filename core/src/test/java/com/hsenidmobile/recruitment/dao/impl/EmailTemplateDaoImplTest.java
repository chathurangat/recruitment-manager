package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.EmailTemplateDao;
import com.hsenidmobile.recruitment.model.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/4/13
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailTemplateDaoImplTest extends CommonDaoTest {
    @Autowired
    private EmailTemplateDao emailTemplateDao;

    public void  createEmailTemplate() {
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.setTemplateType("calling Interview");
        emailTemplate.setBody("You are called for an interview");
        emailTemplate.setSubject("Calling interview");
        emailTemplate.setDate("on");
        emailTemplate.setReceiver("on");
        emailTemplate.setTime("on");
        emailTemplate.setMap("on");
        emailTemplate.setVenue("on");


        //now we will create new cv application section with given data
        emailTemplateDao.create(emailTemplate);
        Assert.assertNotNull(emailTemplate.getId());

        EmailTemplate emailTemplate1Found  = emailTemplateDao.findEmailTemplateById(emailTemplate.getId());
        Assert.assertNotNull(emailTemplate1Found);

        List<EmailTemplate> emailTemplateFound1  = emailTemplateDao.findAllEmailTemplate();
        Assert.assertNotNull(emailTemplateFound1);


    }



    }
