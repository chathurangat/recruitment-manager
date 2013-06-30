package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.model.EmailMessage;
import com.hsenidmobile.recruitment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService{

    @Autowired
    private MailSender mailSender;

    public void sendEmail(EmailMessage emailMessage){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getToAddress());
//        message.setBcc(emailMessage.getBccAddress());
//        message.setCc(emailMessage.getCcAddress());
        message.setSubject(emailMessage.getEmailTemplate().getSubject());
        message.setText(emailMessage.getEmailTemplate().getBody());
        message.setFrom(emailMessage.getFrom());
        message.setReplyTo(emailMessage.getReplyTo());
        //sending the message
        mailSender.send(message);
    }
}
