package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationDao;
import com.hsenidmobile.recruitment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CvApplicationDaoImplTest extends CommonDaoTest{

    @Autowired
    private CvApplicationDao cvApplicationDao;

    @Test
    public void testCreateCvApplication(){

        CvApplication cvApplication = new CvApplication();
        cvApplication.setApplicationName("Application submitted for post of Software Engineer");

        Applicant applicant  = createApplicantUser();
        applicant.submitApplication(cvApplication);

        cvApplication.setCvApplicationTemplate(createCvApplicationTemplate());

        //persist record
        cvApplicationDao.create(cvApplication);
        Assert.assertNotNull(cvApplication);
        Assert.assertNotNull(cvApplication.getId());
        Assert.assertEquals(cvApplication.getApplicationName(),"Application submitted for post of Software Engineer");
        Assert.assertNotNull(cvApplication.getCvApplicationTemplate());
        Assert.assertEquals(cvApplication.getCvApplicationTemplate().getCvHeaderEn(),"Application for Trainee Software Engineer");
        Assert.assertEquals(cvApplication.getCvApplicationTemplate().getCvHeaderSi(),"Application for Trainee Software Engineer_SI");
        Assert.assertEquals(cvApplication.getCvApplicationTemplate().getCvHeaderTa(),"Application for Trainee Software Engineer_TA");

        //removing the inserted record
        cvApplicationDao.remove(cvApplication);
        CvApplication cvApplication1 = cvApplicationDao.findById(cvApplication.getId());
        Assert.assertNull(cvApplication1);
      }


    @Test
    public void testFindCvApplicationOfUser(){

        CvApplication cvApplication = new CvApplication();
        cvApplication.setApplicationName("Application submitted for post of Software Engineer");

        Applicant applicant  = createApplicantUser();
        applicant.submitApplication(cvApplication);

        cvApplication.setCvApplicationTemplate(createCvApplicationTemplate());

        //persist record
        cvApplicationDao.create(cvApplication);
        Assert.assertNotNull(cvApplication);
        Assert.assertNotNull(cvApplication.getId());
        Assert.assertEquals(cvApplication.getApplicationName(),"Application submitted for post of Software Engineer");
        Assert.assertNotNull(cvApplication.getCvApplicationTemplate());
        Assert.assertEquals(cvApplication.getCvApplicationTemplate().getCvHeaderEn(),"Application for Trainee Software Engineer");
        Assert.assertEquals(cvApplication.getCvApplicationTemplate().getCvHeaderSi(),"Application for Trainee Software Engineer_SI");
        Assert.assertEquals(cvApplication.getCvApplicationTemplate().getCvHeaderTa(),"Application for Trainee Software Engineer_TA");


        List<CvApplication> cvApplicationList = cvApplicationDao.findCvApplicationsByApplicant(applicant);
        Assert.assertNotNull(cvApplicationList);
        Assert.assertTrue(cvApplicationList.size()==1);

        //removing the inserted record
        cvApplicationDao.remove(cvApplication);
        CvApplication cvApplication1 = cvApplicationDao.findById(cvApplication.getId());
        Assert.assertNull(cvApplication1);
     }



    private CvApplicationTemplate createCvApplicationTemplate(){
        //creating cv template
        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeaderEn("Application for Trainee Software Engineer");
        cvApplicationTemplate.setCvHeaderSi("Application for Trainee Software Engineer_SI");
        cvApplicationTemplate.setCvHeaderTa("Application for Trainee Software Engineer_TA");


        // creating sections for the cv template
        CvApplicationSection personalDetailsSection = createCvApplicationSection("personal details","personal details_Si","personal details_Ta","1",true);
        CvApplicationSection educationalQualificationSection = createCvApplicationSection("educational qualifications","educational qualifications_Si","educational qualifications_Ta","2",true);
        CvApplicationSection projectWorkSection = createCvApplicationSection("project works","project works_Si","project works_Ta","3",true);

        //adding the cv application sections to array list
        List<CvApplicationSection> cvApplicationSections = new ArrayList<CvApplicationSection>();
        cvApplicationSections.add(personalDetailsSection);
        cvApplicationSections.add(educationalQualificationSection);
        cvApplicationSections.add(projectWorkSection);

        //set up the cv application sections array list to cv template
        cvApplicationTemplate.setCvApplicationSectionList(cvApplicationSections);

        //since we do not have dataDictionary fields we need to create them. if you have those in the database, noo need to create them and just reuse them
        ApplicationFieldDictionary nameDictionaryField = createTextFieldDictionary("name",60);
        nameDictionaryField.setId("1");
        ApplicationFieldDictionary ageDictionaryField = createTextFieldDictionary("age",10);
        ageDictionaryField.setId("2");
        ApplicationFieldDictionary educationalPrimaryDictionaryField = createTextAreaDictionary("primary education", 10, 20);
        educationalPrimaryDictionaryField.setId("3");
        ApplicationFieldDictionary educationalSecondaryDictionaryField = createTextAreaDictionary("secondary education", 10, 20);
        educationalSecondaryDictionaryField.setId("4");
        ApplicationFieldDictionary projectWorksDictionaryField = createTextAreaDictionary("Work Experience",15,30);
        projectWorksDictionaryField.setId("5");

        //now we will create each application field with priority and assign them for the cv application sections
        // we will start with personal details section. there are two fields known as name and age
        CvApplicationField nameField = new CvApplicationField();
        nameField.setPriority(1);
        nameField.setApplicationFieldDictionary(nameDictionaryField);
        nameField.setStatus(true);

        CvApplicationField ageField = new CvApplicationField();
        ageField.setPriority(2);
        ageField.setApplicationFieldDictionary(ageDictionaryField);
        ageField.setStatus(true);

        //we will add those two application fields under the personal details section
        List<CvApplicationField> applicationFieldListForPersonalSection = new ArrayList<CvApplicationField>();
        applicationFieldListForPersonalSection.add(nameField);
        applicationFieldListForPersonalSection.add(ageField);
        personalDetailsSection.setCvApplicationFieldList(applicationFieldListForPersonalSection);

        //likewise we will add application fields for other sections too

        //educational details section
        CvApplicationField primaryEducationField = new CvApplicationField();
        primaryEducationField.setPriority(1);
        primaryEducationField.setApplicationFieldDictionary(educationalPrimaryDictionaryField);
        primaryEducationField.setStatus(true);

        CvApplicationField secondaryEducationField = new CvApplicationField();
        secondaryEducationField.setPriority(2);
        secondaryEducationField.setApplicationFieldDictionary(educationalSecondaryDictionaryField);
        secondaryEducationField.setStatus(true);

        List<CvApplicationField> applicationFieldListForEducationalSection = new ArrayList<CvApplicationField>();
        applicationFieldListForEducationalSection.add(primaryEducationField);
        applicationFieldListForEducationalSection.add(secondaryEducationField);
        educationalQualificationSection.setCvApplicationFieldList(applicationFieldListForEducationalSection);

        //project experience section
        CvApplicationField projectExperienceField = new CvApplicationField();
        projectExperienceField.setApplicationFieldDictionary(projectWorksDictionaryField);
        projectExperienceField.setStatus(true);

        List<CvApplicationField> applicationFieldListProjectExperienceSection = new ArrayList<CvApplicationField>();
        applicationFieldListProjectExperienceSection.add(projectExperienceField);
        projectWorkSection.setCvApplicationFieldList(applicationFieldListProjectExperienceSection);

        return cvApplicationTemplate;
    }


    private CvApplicationSection createCvApplicationSection(String nameEn, String nameSi, String nameTa,String id,boolean status){
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setId(id);
        cvApplicationSection.setSectionNameEn(nameEn);
        cvApplicationSection.setSectionNameSi(nameSi);
        cvApplicationSection.setSectionNameTa(nameTa);
        cvApplicationSection.setStatus(status);
        return cvApplicationSection;
    }


    private TextFieldDictionary createTextFieldDictionary(String label,Integer size){
        TextFieldDictionary textFieldDictionary = new TextFieldDictionary();
        textFieldDictionary.setLabelEn(label);
        textFieldDictionary.setHtmlComponent("TextField");
        textFieldDictionary.setSize(size);
        textFieldDictionary.setStatus(true);
        return textFieldDictionary;
    }

    private TextAreaDictionary createTextAreaDictionary(String label,Integer rows,Integer cols){
        TextAreaDictionary textAreaDictionary = new TextAreaDictionary();
        textAreaDictionary.setLabelEn(label);
        textAreaDictionary.setHtmlComponent("TextArea");
        textAreaDictionary.setRows(rows);
        textAreaDictionary.setCols(cols);
        textAreaDictionary.setStatus(true);
        return textAreaDictionary;
    }

    private Applicant createApplicantUser(){
        Applicant applicant = new Applicant();
        applicant.setId("userId123");
        applicant.setEmail("darshanac@hsenidmobile.com");
        applicant.setUsername("chathurangat");
        applicant.setFirstName("chathuranga");
        applicant.setLastName("tennakoon");
        return applicant;
    }
}
