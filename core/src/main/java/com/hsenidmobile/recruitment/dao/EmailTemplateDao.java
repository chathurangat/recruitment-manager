package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.EmailTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/2/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface EmailTemplateDao {

    void create(EmailTemplate emailTemplate);
    /**
    * <p>
    *     create cv email template
    * </p>
    * @param emailTemplate as {@link com.hsenidmobile.recruitment.model.CvApplicationSection}
    */

    void update(EmailTemplate emailTemplate);
    /**
     * <p>
     *     update cv email template
     * </p>
     * @param emailTemplate as {@link com.hsenidmobile.recruitment.model.CvApplicationSection}
     */


    void removeEmailTemplate(EmailTemplate emailTemplate);

    /**
     * <p>
     *     find email template with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link EmailTemplate}
     */

    EmailTemplate findEmailTemplateById(String id);
    /**
     * <p>
     *     find email template with given id
     * </p>
     * @return instance of {@link EmailTemplate}
     */


    List<EmailTemplate> findAllEmailTemplate();



}
