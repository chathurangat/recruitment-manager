package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.service.ValidationService;
import org.springframework.stereotype.Service;

@Service("validationService")
public class ValidationServiceImpl implements ValidationService{

    private static final String EMAIL_PATTERN = "";
    private static final String PHONE_NUMBER_PATTERN = "";

    @Override
    public boolean isValidEmail(String email) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isRequiredField(String fieldValue, String htmlComponent) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
