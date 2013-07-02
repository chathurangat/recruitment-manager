package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.EmailTemplateDao;
import com.hsenidmobile.recruitment.model.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/2/13
 * Time: 9:43 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository("emailTemplateDao")
public class EmailTemplateDaoImpl implements EmailTemplateDao {
    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "email_template_section";
    @Override
    public void create(EmailTemplate emailTemplate){
        if (!mongoTemplate.collectionExists(EmailTemplate.class)) {
            mongoTemplate.createCollection(EmailTemplate.class);
        }
        mongoTemplate.insert(emailTemplate, COLLECTION_NAME);
    }
    @Override
    public void update(EmailTemplate emailTemplate){
        mongoTemplate.save(emailTemplate,COLLECTION_NAME);
    }


    @Override
    public void removeEmailTemplate(EmailTemplate emailTemplate) {
        mongoTemplate.remove(emailTemplate,COLLECTION_NAME);
    }

    @Override
    public EmailTemplate findEmailTemplateById(String id) {
        return mongoTemplate.findById(id,EmailTemplate.class,COLLECTION_NAME);
    }
    @Override
    public List<EmailTemplate> findAllEmailTemplate() {
        return mongoTemplate.findAll(EmailTemplate.class);
    }



}
