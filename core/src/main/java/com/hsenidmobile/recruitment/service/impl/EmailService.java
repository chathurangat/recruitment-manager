package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.model.EmailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailService extends Thread {

    private EmailMessage emailMessage;
    private MailSender mailSender;

    public EmailService(){

    }

    public EmailService(EmailMessage emailMessage, MailSender mailSender){
        this.emailMessage=emailMessage;
        this.mailSender = mailSender;
    }

    public void run(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getToAddress());
        message.setSubject(emailMessage.getEmailTemplate().getSubject());
        message.setText(emailMessage.getEmailTemplate().getBody());
        message.setFrom(emailMessage.getFrom());
        message.setReplyTo(emailMessage.getReplyTo());
        //sending the message
        mailSender.send(message);
    }
}
