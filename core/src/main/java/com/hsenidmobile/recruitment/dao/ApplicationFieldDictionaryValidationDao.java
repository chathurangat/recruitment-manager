package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionaryValidation;

import java.util.List;

public interface ApplicationFieldDictionaryValidationDao {

    String create(ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation);

    ApplicationFieldDictionaryValidation findValidationById(String Id);

    void remove(ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation);

    List<ApplicationFieldDictionaryValidation> getAllValidationCriteria();
}
