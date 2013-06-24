package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationFieldDao;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CvApplicationFieldDaoImplTest extends CommonDaoTest {

    @Autowired
    private CvApplicationFieldDao cvApplicationFieldDao;

    @Test
    public void CreateCvSectionField() {

         //create cv application section field
        CvApplicationField cvApplicationField = new CvApplicationField();
        cvApplicationField.setPriority(1);
        cvApplicationField.setStatus(true);

        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        applicationFieldDictionary.setLabelEn("First Name");
        applicationFieldDictionary.setLabelSi("First Name_Si");
        applicationFieldDictionary.setLabelTa("First Name_Ta");
        applicationFieldDictionary.setHtmlComponent("TextField");
        applicationFieldDictionary.setDescription("First Name Details");
        applicationFieldDictionary.setStatus(true);

        cvApplicationField.setApplicationFieldDictionary(applicationFieldDictionary);

        //now we will create new cv application section field with given data
        cvApplicationFieldDao.createCvSectionField(cvApplicationField);
        Assert.assertNotNull(cvApplicationField.getId());

        CvApplicationField cvApplicationFieldFound  = cvApplicationFieldDao.findCvSectionFieldById(cvApplicationField.getId());
        Assert.assertNotNull(cvApplicationFieldFound);

    /*    //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationFieldDao.removeCvSectionField(cvApplicationField);
        CvApplicationField cvApplicationField1 = cvApplicationFieldDao.findCvSectionFieldById(cvApplicationField.getId());
        Assert.assertNull(cvApplicationField1);
     */
    }

    public void UpdateCvSectionField(){
        //todo write upate cv section field method here
    }

}
