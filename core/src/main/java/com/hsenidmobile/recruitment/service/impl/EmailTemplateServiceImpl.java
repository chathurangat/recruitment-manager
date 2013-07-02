package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.EmailTemplateDao;
import com.hsenidmobile.recruitment.model.EmailTemplate;
import com.hsenidmobile.recruitment.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/2/13
 * Time: 9:46 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("emailTemplateService")
public class EmailTemplateServiceImpl implements EmailTemplateService {
    @Autowired
    private EmailTemplateDao emailTemplateDao;


    @Override
    public void create(EmailTemplate emailTemplate) {
        emailTemplateDao.create(emailTemplate);
    }

    @Override
    public void update(EmailTemplate emailTemplate){
        emailTemplateDao.update(emailTemplate);
    }

    @Override
    public void removeEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplateDao.removeEmailTemplate(emailTemplate);
    }

    @Override
    public EmailTemplate findEmailTemplateById(String id) {
        return emailTemplateDao.findEmailTemplateById(id);
    }

    @Override
    public List<EmailTemplate> findAllEmailTemplate(){
        return emailTemplateDao.findAllEmailTemplate();
    }


}
