package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.model.EmailMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component("emailSender")
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;


    public void sendEmail(final String toEmailAddresses, final String fromEmailAddress,
                          final String subject) {
        sendEmail(toEmailAddresses, fromEmailAddress, subject, null, null,null);
    }

//    public void sendEmailWithAttachment(final String toEmailAddresses, final String fromEmailAddress,
//                                        final String subject, final String attachmentPath,
//                                        final String attachmentName) {
//        sendEmail(toEmailAddresses, fromEmailAddress, subject, attachmentPath, attachmentName);
//    }

//    private void sendEmail(final String toEmailAddresses, final String fromEmailAddress,
//                           final String subject, final String attachmentPath,
//                           final String attachmentName) {
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
//                message.setTo(toEmailAddresses);
//                message.setFrom(new InternetAddress(fromEmailAddress));
//                message.setSubject(subject);
//                String body = VelocityEngineUtils.mergeTemplateIntoString(
//                        velocityEngine, "templates/reminder.vm", "UTF-8", null);
//                message.setText(body, true);
//                if (!StringUtils.isBlank(attachmentPath)) {
//                    FileSystemResource file = new FileSystemResource(attachmentPath);
//                    message.addAttachment(attachmentName, file);
//                }
//            }
//        };
//        this.mailSender.send(preparator);
//    }



    private void sendEmail(final String toEmailAddresses, final String fromEmailAddress,
                           final String subject, final String attachmentPath,
                           final String attachmentName,final String image) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(toEmailAddresses);
                message.setFrom(new InternetAddress(fromEmailAddress));
                message.setSubject(subject);
                String body = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "templates/reminder.vm", "UTF-8", null);
                message.setText(body, true);
                //  message.addInline(image, new ClassPathResource("templates/staticmap.png"));
                message.addInline("image", new ClassPathResource("templates/staticmap.png"));
                if (!StringUtils.isBlank(attachmentPath)) {
                    FileSystemResource file = new FileSystemResource(attachmentPath);
                    message.addAttachment(attachmentName, file);
                }
            }
        };
        this.mailSender.send(preparator);
    }



    public void sendEmail(final EmailMessage emailMessage){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(emailMessage.getToAddress());
                message.setFrom(new InternetAddress(emailMessage.getFrom()));
                message.setSubject(emailMessage.getEmailTemplate().getSubject());
                String body = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "templates/reminder.vm", "UTF-8", null);
                message.setText(body, true);
                FileSystemResource img = new FileSystemResource(new File("templates/staticmap.png"));
                message.addInline("imgId", img);
//               if (!StringUtils.isBlank(attachmentPath)) {
//                   FileSystemResource file = new FileSystemResource(attachmentPath);
//                   message.addAttachment(attachmentName, file);
//               }
            }
        };
        //sending email as a thread
        EmailService emailService = new EmailService(preparator,mailSender);
        emailService.start();
    }
}
