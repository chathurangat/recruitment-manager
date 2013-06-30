package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.EmailMessage;

public interface EmailService {
    /**
     * <p>
     *     sending the email with given email message
     * </p>
     * @param emailMessage as instance of {@link EmailMessage}
     */
    void sendEmail(EmailMessage emailMessage);
}
