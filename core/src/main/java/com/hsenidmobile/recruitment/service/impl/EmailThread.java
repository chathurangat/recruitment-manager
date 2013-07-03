package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.model.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: hsenid
 * Date: 7/2/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */

public class EmailThread extends Thread {

    private EmailMessage emailMessage;
    private MailSender mailSender;

    public EmailThread(){

    }
    public EmailThread(EmailMessage emailMessage,MailSender mailSender){
        this.emailMessage=emailMessage;
        this.mailSender = mailSender;
    }
    public void run(){
        System.out.println(" mail sender ["+mailSender+"] sender ");
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
