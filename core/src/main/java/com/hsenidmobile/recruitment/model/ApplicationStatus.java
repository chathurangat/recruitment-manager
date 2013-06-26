package com.hsenidmobile.recruitment.model;


public enum ApplicationStatus {
    SUBMITTED("submitted"), SHORTLISTED("shortlisted");

    private String statusCode;

    private ApplicationStatus(String s) {
        statusCode = s;
    }

    public String getStatusCode() {
        return statusCode;
    }

}
