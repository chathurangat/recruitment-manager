package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

public class EmailTemplate implements Serializable {
    @Id
    private String id;
    @NotBlank
    private String templateType;
    @NotBlank
    private String subject;
    @NotBlank
    private String receiver;
    @NotBlank
    private String body;
    private String date;
    private String time;
    private String venue;
    private String map;

    private List<EmailTemplate> emailTemplateList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }


    public List<EmailTemplate> getEmailTemplateList() {
        return emailTemplateList;
    }

    public void setEmailTemplateList(List<EmailTemplate> emailTemplateList) {
        this.emailTemplateList = emailTemplateList;
    }

   }
