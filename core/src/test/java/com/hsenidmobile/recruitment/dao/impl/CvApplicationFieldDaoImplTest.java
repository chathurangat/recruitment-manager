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
        cvApplicationField.setFieldValue("first_name");

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

        //make sure the values are correct when create cv application section field using 'createCvSectionField' method
        Assert.assertNotNull(cvApplicationField);
        Assert.assertEquals(cvApplicationField.getFieldValue(),"first_name");
        Assert.assertEquals(cvApplicationField.getPriority(),1,0);
        Assert.assertTrue(cvApplicationField.isStatus());
        Assert.assertNotNull(cvApplicationField.getApplicationFieldDictionary());
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getLabelEn(),"First Name");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getLabelSi(),"First Name_Si");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getLabelTa(),"First Name_Ta");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getHtmlComponent(),"TextField");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getDescription(),"First Name Details");
        Assert.assertTrue(cvApplicationField.getApplicationFieldDictionary().isStatus());

        //make sure the values are correct when retrieving  cv application section field using 'findCvSectionFieldById' method
        CvApplicationField cvApplicationFieldFound  = cvApplicationFieldDao.findCvSectionFieldById(cvApplicationField.getId());
        Assert.assertNotNull(cvApplicationFieldFound);
        Assert.assertEquals(cvApplicationFieldFound.getFieldValue(),"first_name");
        Assert.assertEquals(cvApplicationFieldFound.getPriority(),1,0);
        Assert.assertTrue(cvApplicationFieldFound.isStatus());
        Assert.assertNotNull(cvApplicationFieldFound.getApplicationFieldDictionary());
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getLabelEn(),"First Name");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getLabelSi(),"First Name_Si");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getLabelTa(),"First Name_Ta");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getHtmlComponent(),"TextField");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getDescription(),"First Name Details");
        Assert.assertTrue(cvApplicationFieldFound.getApplicationFieldDictionary().isStatus());

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
       //make sure the values are exists when removing a cv application section field using 'removeCvSectionField' method
        cvApplicationFieldDao.removeCvSectionField(cvApplicationField);
        CvApplicationField cvApplicationField1 = cvApplicationFieldDao.findCvSectionFieldById(cvApplicationField.getId());
        Assert.assertNull(cvApplicationField1);

    }

    @Test
    public void UpdateCvSectionField(){
        //update cv application section field
        CvApplicationField cvApplicationField = new CvApplicationField();
        cvApplicationField.setPriority(1);
        cvApplicationField.setStatus(true);
        cvApplicationField.setFieldValue("full_name");

        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        applicationFieldDictionary.setLabelEn("Full Name");
        applicationFieldDictionary.setLabelSi("Full Name_Si");
        applicationFieldDictionary.setLabelTa("Full Name_Ta");
        applicationFieldDictionary.setHtmlComponent("TextField");
        applicationFieldDictionary.setDescription("Full Name Details");
        applicationFieldDictionary.setStatus(true);

        cvApplicationField.setApplicationFieldDictionary(applicationFieldDictionary);

        //now we will update cv application section field with given data
        cvApplicationFieldDao.updateCvSectionField(cvApplicationField);

        //make sure the values are correct when update cv application section field using 'updateCvSectionField' method
        Assert.assertNotNull(cvApplicationField);
        Assert.assertEquals(cvApplicationField.getFieldValue(),"full_name");
        Assert.assertEquals(cvApplicationField.getPriority(),1,0);
        Assert.assertTrue(cvApplicationField.isStatus());
        Assert.assertNotNull(cvApplicationField.getApplicationFieldDictionary());
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getLabelEn(),"Full Name");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getLabelSi(),"Full Name_Si");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getLabelTa(),"Full Name_Ta");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getHtmlComponent(),"TextField");
        Assert.assertEquals(cvApplicationField.getApplicationFieldDictionary().getDescription(),"Full Name Details");
        Assert.assertTrue(cvApplicationField.getApplicationFieldDictionary().isStatus());

        //make sure the values are correct when retrieving  cv application section field using 'findCvSectionFieldById' method
        CvApplicationField cvApplicationFieldFound  = cvApplicationFieldDao.findCvSectionFieldById(cvApplicationField.getId());
        Assert.assertNotNull(cvApplicationFieldFound);
        Assert.assertEquals(cvApplicationFieldFound.getFieldValue(),"full_name");
        Assert.assertEquals(cvApplicationFieldFound.getPriority(),1,0);
        Assert.assertTrue(cvApplicationFieldFound.isStatus());
        Assert.assertNotNull(cvApplicationFieldFound.getApplicationFieldDictionary());
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getLabelEn(),"Full Name");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getLabelSi(),"Full Name_Si");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getLabelTa(),"Full Name_Ta");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getHtmlComponent(),"TextField");
        Assert.assertEquals(cvApplicationFieldFound.getApplicationFieldDictionary().getDescription(),"Full Name Details");
        Assert.assertTrue(cvApplicationFieldFound.getApplicationFieldDictionary().isStatus());

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        //make sure the values are exists when removing a cv application section field using 'removeCvSectionField' method
        cvApplicationFieldDao.removeCvSectionField(cvApplicationField);
        CvApplicationField cvApplicationField1 = cvApplicationFieldDao.findCvSectionFieldById(cvApplicationField.getId());
        Assert.assertNull(cvApplicationField1);
    }

}
