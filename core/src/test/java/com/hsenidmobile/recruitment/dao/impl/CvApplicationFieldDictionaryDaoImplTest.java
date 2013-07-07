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

        //make sure the values are correct when create cv application section field using 'createCvSectionField' method
        Assert.assertNotNull(applicationFieldDictionary);
        Assert.assertNotNull(applicationFieldDictionary.getId());
        Assert.assertEquals(applicationFieldDictionary.getLabelEn(),"Full Name");
        Assert.assertEquals(applicationFieldDictionary.getLabelSi(),"Full Name_Si");
        Assert.assertEquals(applicationFieldDictionary.getLabelTa(),"Full Name_Ta");
        Assert.assertEquals(applicationFieldDictionary.getHtmlComponent(),"TextField");
        Assert.assertEquals(applicationFieldDictionary.getDescription(),"full name");
        Assert.assertTrue(applicationFieldDictionary.isStatus());

        //make sure the values are correct when retrieving  cv application section field using 'findCvSectionFieldById' method
        ApplicationFieldDictionary applicationFieldDictionaryFound  = cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(applicationFieldDictionary.getId());
        Assert.assertNotNull(applicationFieldDictionaryFound);
        Assert.assertNotNull(applicationFieldDictionaryFound.getId());
        Assert.assertEquals(applicationFieldDictionaryFound.getLabelEn(),"Full Name");
        Assert.assertEquals(applicationFieldDictionaryFound.getLabelSi(),"Full Name_Si");
        Assert.assertEquals(applicationFieldDictionaryFound.getLabelTa(),"Full Name_Ta");
        Assert.assertEquals(applicationFieldDictionaryFound.getHtmlComponent(),"TextField");
        Assert.assertEquals(applicationFieldDictionaryFound.getDescription(),"full name");
        Assert.assertTrue(applicationFieldDictionaryFound.isStatus());

       //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
       //make sure the values are exists when removing a cv application section field using 'removeCvSectionField' method
        cvApplicationFieldDictionaryDao.removeCvSectionFieldDictionary(applicationFieldDictionary);
        ApplicationFieldDictionary applicationFieldDictionaryFound1 = cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(applicationFieldDictionary.getId());
        Assert.assertNull(applicationFieldDictionaryFound1);
    }

    @Test
    public void UpdateCvSectionFieldDictionary(){

        //update cv application section field dictionary
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();

        applicationFieldDictionary.setLabelEn("Full Name");
        applicationFieldDictionary.setLabelSi("Full Name Si");
        applicationFieldDictionary.setLabelTa("Full Name Ta");
        applicationFieldDictionary.setHtmlComponent("TextField");
        applicationFieldDictionary.setDescription("full name");
        applicationFieldDictionary.setStatus(true);

        //now we will update cv application section field dictionary with given data
        cvApplicationFieldDictionaryDao.updateCvSectionFieldDictionary(applicationFieldDictionary);

        //make sure the values are correct when create cv application section field using 'createCvSectionField' method
        Assert.assertNotNull(applicationFieldDictionary);
        Assert.assertNotNull(applicationFieldDictionary.getId());
        Assert.assertEquals(applicationFieldDictionary.getLabelEn(),"Full Name");
        Assert.assertEquals(applicationFieldDictionary.getLabelSi(),"Full Name Si");
        Assert.assertEquals(applicationFieldDictionary.getLabelTa(),"Full Name Ta");
        Assert.assertEquals(applicationFieldDictionary.getHtmlComponent(),"TextField");
        Assert.assertEquals(applicationFieldDictionary.getDescription(),"full name");
        Assert.assertTrue(applicationFieldDictionary.isStatus());

        //make sure the values are correct when retrieving cv application section field using 'findCvSectionFieldById' method
        ApplicationFieldDictionary applicationFieldDictionaryFound  = cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(applicationFieldDictionary.getId());
        Assert.assertNotNull(applicationFieldDictionaryFound);
        Assert.assertNotNull(applicationFieldDictionaryFound.getId());
        Assert.assertEquals(applicationFieldDictionaryFound.getLabelEn(),"Full Name");
        Assert.assertEquals(applicationFieldDictionaryFound.getLabelSi(),"Full Name Si");
        Assert.assertEquals(applicationFieldDictionaryFound.getLabelTa(),"Full Name Ta");
        Assert.assertEquals(applicationFieldDictionaryFound.getHtmlComponent(),"TextField");
        Assert.assertEquals(applicationFieldDictionaryFound.getDescription(),"full name");
        Assert.assertTrue(applicationFieldDictionaryFound.isStatus());

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        //make sure the values are exists when removing a cv application section field using 'removeCvSectionField' method
        cvApplicationFieldDictionaryDao.removeCvSectionFieldDictionary(applicationFieldDictionary);
        ApplicationFieldDictionary applicationFieldDictionaryFound1 = cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(applicationFieldDictionary.getId());
        Assert.assertNull(applicationFieldDictionaryFound1);
    }
}
