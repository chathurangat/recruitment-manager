package com.hsenidmobile.recruitment.model;

import java.util.List;

public class EmailMessage {

    private String id;

    private EmailTemplate emailTemplate;
    private String bccAddress[];
    private String ccAddress;
    private String toAddress;
    private String from;
    private String replyTo;
    private List<EmailTemplate> emailSectionList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public EmailTemplate getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(EmailTemplate emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public String[] getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String[] bccAddress) {
        this.bccAddress = bccAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }


    public void setEmailSectionList(List<EmailTemplate> emailSectionList) {
        this.emailSectionList = emailSectionList;
    }

    public List<EmailTemplate> getEmailSectionList() {
        return emailSectionList;
    }
}
