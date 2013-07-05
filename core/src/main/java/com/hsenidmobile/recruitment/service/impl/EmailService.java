package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.model.EmailMessage;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailService extends Thread {

    private EmailMessage emailMessage;
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    private MimeMessagePreparator mimeMessagePreparator;

    public EmailService(){

    }


    public EmailService(EmailMessage emailMessage, JavaMailSender mailSender){
        this.emailMessage=emailMessage;
        this.mailSender = mailSender;
    }


    public EmailService(MimeMessagePreparator mimeMessagePreparator, JavaMailSender mailSender){
        this.mailSender = mailSender;
        this.mimeMessagePreparator = mimeMessagePreparator;
    }

    public void run(){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(emailMessage.getToAddress());
//        message.setSubject(emailMessage.getEmailTemplate().getSubject());
//        message.setText(emailMessage.getEmailTemplate().getBody());
//        message.setFrom(emailMessage.getFrom());
//        message.setReplyTo(emailMessage.getReplyTo());
//        //sending the message
//        mailSender.send(message);
        mailSender.send(mimeMessagePreparator);
    }
}
