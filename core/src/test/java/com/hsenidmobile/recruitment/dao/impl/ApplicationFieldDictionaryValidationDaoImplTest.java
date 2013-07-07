package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.ApplicationFieldDictionaryValidationDao;
import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionaryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class ApplicationFieldDictionaryValidationDaoImplTest extends CommonDaoTest{

    @Autowired
    private ApplicationFieldDictionaryValidationDao applicationFieldDictionaryValidationDao;

    @Test
    public void create(){
        this.createValidationCriteria("PHONE_NUMBER");
    }


    private ApplicationFieldDictionaryValidation createValidationCriteria(String criteriaName){
        ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation =  new  ApplicationFieldDictionaryValidation();
        applicationFieldDictionaryValidation.setValidationCriteria(criteriaName);
        applicationFieldDictionaryValidationDao.create(applicationFieldDictionaryValidation);
        assertNotNull(applicationFieldDictionaryValidation.getId());
        return applicationFieldDictionaryValidation;
    }
}
