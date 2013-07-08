package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationSectionDao;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CvApplicationSectionDaoImplTest extends CommonDaoTest{

    @Autowired
    private CvApplicationSectionDao cvApplicationSectionDao;
    @Test
    public void  createCvSection() {

        //creating cv section
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setSectionNameEn("personal details");
        cvApplicationSection.setSectionNameSi("personal details_Si");
        cvApplicationSection.setSectionNameTa("personal details_Ta");
        cvApplicationSection.setStatus(true);
        cvApplicationSection.setPriority(1);

        //create a application field dictionary element for First Name
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        applicationFieldDictionary.setLabelEn("First Name");
        applicationFieldDictionary.setLabelSi("First Name si");
        applicationFieldDictionary.setLabelSi("First Name ta");
        applicationFieldDictionary.setHtmlComponent("TextField");
        applicationFieldDictionary.setStatus(true);
        applicationFieldDictionary.setDescription("first name of applicant");

        //create a application field dictionary element for Address
        ApplicationFieldDictionary applicationFieldDictionary1 = new ApplicationFieldDictionary();
        applicationFieldDictionary1.setLabelEn("Address");
        applicationFieldDictionary1.setLabelSi("Address si");
        applicationFieldDictionary1.setLabelSi("Address ta");
        applicationFieldDictionary1.setHtmlComponent("TextArea");
        applicationFieldDictionary1.setStatus(true);
        applicationFieldDictionary1.setDescription("Address of applicant");

        //create a Cv application field for first name (textfield)
        CvApplicationField cvApplicationField = new CvApplicationField();
        cvApplicationField.setFieldValue("first_name");
        cvApplicationField.setStatus(true);
        cvApplicationField.setPriority(1);
        cvApplicationField.setApplicationFieldDictionary(applicationFieldDictionary);

        //create a Cv application field for address (textarea)
        CvApplicationField cvApplicationField1 = new CvApplicationField();
        cvApplicationField1.setFieldValue("address");
        cvApplicationField1.setStatus(true);
        cvApplicationField1.setPriority(2);
        cvApplicationField1.setApplicationFieldDictionary(applicationFieldDictionary1);


        //adding the Cv application fields to array list
        List<CvApplicationField> cvApplicationFieldList = new ArrayList<CvApplicationField>();
        cvApplicationFieldList.add(cvApplicationField);
        cvApplicationFieldList.add(cvApplicationField1);

        //setting setCvApplicationFieldList to cvApplicationSection
        cvApplicationSection.setCvApplicationFieldList(cvApplicationFieldList);

        //now we will create new cv application section with given data
        //make sure the values are correct when create new cv application section using 'create' method
        cvApplicationSectionDao.create(cvApplicationSection);
        Assert.assertNotNull(cvApplicationSection);
        Assert.assertNotNull(cvApplicationSection.getId());
        Assert.assertEquals(cvApplicationSection.getSectionNameEn(),"personal details");
        Assert.assertEquals(cvApplicationSection.getSectionNameSi(),"personal details_Si");
        Assert.assertEquals(cvApplicationSection.getSectionNameTa(),"personal details_Ta");
        Assert.assertEquals(cvApplicationSection.getPriority(),1,0);
        Assert.assertTrue(cvApplicationSection.isStatus());
        Assert.assertNotNull(cvApplicationSection.getCvApplicationFieldList());

        CvApplicationSection cvApplicationSectionFound  = cvApplicationSectionDao.findCvSectionById(cvApplicationSection.getId());
        Assert.assertNotNull(cvApplicationSectionFound);
        Assert.assertNotNull(cvApplicationSectionFound.getId());
        Assert.assertEquals(cvApplicationSectionFound.getSectionNameEn(),"personal details");
        Assert.assertEquals(cvApplicationSectionFound.getSectionNameSi(),"personal details_Si");
        Assert.assertEquals(cvApplicationSectionFound.getSectionNameTa(),"personal details_Ta");
        Assert.assertEquals(cvApplicationSectionFound.getPriority(),1,0);
        Assert.assertTrue(cvApplicationSectionFound.isStatus());
        Assert.assertNotNull(cvApplicationSectionFound.getCvApplicationFieldList());

        List<CvApplicationSection> cvApplicationSectionFound1  = cvApplicationSectionDao.findAllCvSection();
        Assert.assertNotNull(cvApplicationSectionFound1);

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationSectionDao.removeCvSection(cvApplicationSection);
        CvApplicationSection cvApplicationSection1 = cvApplicationSectionDao.findCvSectionById(cvApplicationSection.getId());
        Assert.assertNull(cvApplicationSection1);
    }

    @Test
    public void  updateCvSection() {

        //creating cv section
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setSectionNameEn("Personal Details");
        cvApplicationSection.setSectionNameSi("Personal Details Si");
        cvApplicationSection.setSectionNameTa("Personal Details Ta");
        cvApplicationSection.setStatus(true);
        cvApplicationSection.setPriority(1);

        //update a application field dictionary element for Full Name
        ApplicationFieldDictionary applicationFieldDictionary = new ApplicationFieldDictionary();
        applicationFieldDictionary.setLabelEn("Full Name");
        applicationFieldDictionary.setLabelSi("Full Name si");
        applicationFieldDictionary.setLabelSi("FullName ta");
        applicationFieldDictionary.setHtmlComponent("TextField");
        applicationFieldDictionary.setStatus(true);
        applicationFieldDictionary.setDescription("full name of applicant");

        //update a application field dictionary element for Permanent Address
        ApplicationFieldDictionary applicationFieldDictionary1 = new ApplicationFieldDictionary();
        applicationFieldDictionary1.setLabelEn("Permanent Address");
        applicationFieldDictionary1.setLabelSi("Permanent Address si");
        applicationFieldDictionary1.setLabelSi("Permanent Address ta");
        applicationFieldDictionary1.setHtmlComponent("TextArea");
        applicationFieldDictionary1.setStatus(true);
        applicationFieldDictionary1.setDescription("Permanent Address of applicant");

        //update a Cv application field for full name (textfield)
        CvApplicationField cvApplicationField = new CvApplicationField();
        cvApplicationField.setFieldValue("full_name");
        cvApplicationField.setStatus(true);
        cvApplicationField.setPriority(1);
        cvApplicationField.setApplicationFieldDictionary(applicationFieldDictionary);

        //update a Cv application field for address (textarea)
        CvApplicationField cvApplicationField1 = new CvApplicationField();
        cvApplicationField1.setFieldValue("permanent_address");
        cvApplicationField1.setStatus(true);
        cvApplicationField1.setPriority(2);
        cvApplicationField1.setApplicationFieldDictionary(applicationFieldDictionary1);

        //adding the Cv application fields to array list
        List<CvApplicationField> cvApplicationFieldList = new ArrayList<CvApplicationField>();
        cvApplicationFieldList.add(cvApplicationField);
        cvApplicationFieldList.add(cvApplicationField1);

        //setting setCvApplicationFieldList to cvApplicationSection
        cvApplicationSection.setCvApplicationFieldList(cvApplicationFieldList);

        //now we will update cv application section with given data
        //make sure the values are correct when create new cv application section using 'update' method
        cvApplicationSectionDao.update(cvApplicationSection);
        Assert.assertNotNull(cvApplicationSection);
        Assert.assertNotNull(cvApplicationSection.getId());
        Assert.assertEquals(cvApplicationSection.getSectionNameEn(),"Personal Details");
        Assert.assertEquals(cvApplicationSection.getSectionNameSi(),"Personal Details Si");
        Assert.assertEquals(cvApplicationSection.getSectionNameTa(),"Personal Details Ta");
        Assert.assertEquals(cvApplicationSection.getPriority(),1,0);
        Assert.assertTrue(cvApplicationSection.isStatus());
        Assert.assertNotNull(cvApplicationSection.getCvApplicationFieldList());

        CvApplicationSection cvApplicationSectionFound  = cvApplicationSectionDao.findCvSectionById(cvApplicationSection.getId());
        Assert.assertNotNull(cvApplicationSectionFound);
        Assert.assertNotNull(cvApplicationSectionFound.getId());
        Assert.assertEquals(cvApplicationSectionFound.getSectionNameEn(),"Personal Details");
        Assert.assertEquals(cvApplicationSectionFound.getSectionNameSi(),"Personal Details Si");
        Assert.assertEquals(cvApplicationSectionFound.getSectionNameTa(),"Personal Details Ta");
        Assert.assertEquals(cvApplicationSectionFound.getPriority(),1,0);
        Assert.assertTrue(cvApplicationSectionFound.isStatus());
        Assert.assertNotNull(cvApplicationSectionFound.getCvApplicationFieldList());

        List<CvApplicationSection> cvApplicationSectionFound1  = cvApplicationSectionDao.findAllCvSection();
        Assert.assertNotNull(cvApplicationSectionFound1);

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationSectionDao.removeCvSection(cvApplicationSection);
        CvApplicationSection cvApplicationSection1 = cvApplicationSectionDao.findCvSectionById(cvApplicationSection.getId());
        Assert.assertNull(cvApplicationSection1);
    }
}
