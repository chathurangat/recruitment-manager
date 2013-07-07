package com.hsenidmobile.recruitment.service;

public interface ValidationService {

    boolean isValidEmail(String email);

    boolean isValidPhoneNumber(String phoneNumber);

    boolean isRequiredField(String fieldValue,String htmlComponent);
}
