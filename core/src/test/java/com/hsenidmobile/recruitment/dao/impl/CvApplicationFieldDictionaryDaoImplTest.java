package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationFieldDictionaryDao;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CvApplicationFieldDictionaryDaoImplTest extends CommonDaoTest{

    @Autowired
    private CvApplicationFieldDictionaryDao cvApplicationFieldDictionaryDao;

    @Test
    public void CreateCvSectionFieldDictionary() {

        //create cv application section field dictionary
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();

        applicationFieldDictionary.setLabelEn("Full Name");
        applicationFieldDictionary.setLabelSi("Full Name_Si");
        applicationFieldDictionary.setLabelTa("Full Name_Ta");
        applicationFieldDictionary.setHtmlComponent("TextField");
        applicationFieldDictionary.setDescription("full name");
        applicationFieldDictionary.setStatus(true);

        //now we will create new cv application section field dictionary with given data
        cvApplicationFieldDictionaryDao.createCvSectionFieldDictionary(applicationFieldDictionary);
        Assert.assertNotNull(applicationFieldDictionary.getId());

        ApplicationFieldDictionary applicationFieldDictionaryFound  = cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(applicationFieldDictionary.getId());
        Assert.assertNotNull(applicationFieldDictionaryFound);

       //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationFieldDictionaryDao.removeCvSectionFieldDictionary(applicationFieldDictionary);
        ApplicationFieldDictionary applicationFieldDictionaryFound1 = cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(applicationFieldDictionary.getId());
        Assert.assertNull(applicationFieldDictionaryFound1);

    }

    public void UpdateCvSectionFieldDictionary(){
       //todo write update cv section field dictionary method here
    }
}
