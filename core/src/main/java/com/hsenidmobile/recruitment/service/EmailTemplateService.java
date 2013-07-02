package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.EmailTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/2/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */
public interface EmailTemplateService {

    /**
     * <p>
     *     update email template
     * </p>
     * @param emailTemplate as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
     void create(EmailTemplate emailTemplate);
    /**
     * <p>
     *     create email template
     * </p>
     * @param emailTemplate as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    void update(EmailTemplate emailTemplate);

    void removeEmailTemplate(EmailTemplate emailTemplate);

    EmailTemplate findEmailTemplateById(String id);

    List<EmailTemplate> findAllEmailTemplate();
}
