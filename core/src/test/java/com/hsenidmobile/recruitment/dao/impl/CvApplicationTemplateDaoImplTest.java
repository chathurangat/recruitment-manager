package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationTemplateDao;
import com.hsenidmobile.recruitment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CvApplicationTemplateDaoImplTest extends CommonDaoTest{

    @Autowired
    private CvApplicationTemplateDao cvApplicationTemplateDao;

    @Test
    public void createCvTemplate(){

        //creating cv template
        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeaderEn("Application for Trainee Software Engineer");
        cvApplicationTemplate.setCvHeaderSi("Application for Trainee Software Engineer_SI");
        cvApplicationTemplate.setCvHeaderTa("Application for Trainee Software Engineer_TA");
        cvApplicationTemplate.setStatus(true);

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
        ApplicationFieldDictionary nameDictionaryField = createTextFieldDictionary("name","nameSi","nameTa",60);
        nameDictionaryField.setId("1");
        ApplicationFieldDictionary ageDictionaryField = createTextFieldDictionary("age","ageSi","ageTa",10);
        ageDictionaryField.setId("2");
        ApplicationFieldDictionary educationalPrimaryDictionaryField = createTextAreaDictionary("primary education","primary educationSi","primary educationTa", 10, 20);
        educationalPrimaryDictionaryField.setId("3");
        ApplicationFieldDictionary educationalSecondaryDictionaryField = createTextAreaDictionary("secondary education","secondary educationSi","secondary educationTa", 10, 20);
        educationalSecondaryDictionaryField.setId("4");
        ApplicationFieldDictionary projectWorksDictionaryField = createTextAreaDictionary("Work Experience","Work ExperienceSi","Work ExperienceTa",15,30);
        projectWorksDictionaryField.setId("5");

        //now we will create each application field with priority and assign them for the cv application sections
        // we will start with personal details section. there are two fields known as name and age
        CvApplicationField nameField = new CvApplicationField();
        nameField.setId("name_1");
        nameField.setPriority(1);
        nameField.setApplicationFieldDictionary(nameDictionaryField);
        nameField.setStatus(true);

        CvApplicationField ageField = new CvApplicationField();
        ageField.setId("age_2");
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
        primaryEducationField.setId("primary_3");
        primaryEducationField.setPriority(1);
        primaryEducationField.setApplicationFieldDictionary(educationalPrimaryDictionaryField);
        primaryEducationField.setStatus(true);

        CvApplicationField secondaryEducationField = new CvApplicationField();
        secondaryEducationField.setId("secondary_4");
        secondaryEducationField.setPriority(2);
        secondaryEducationField.setApplicationFieldDictionary(educationalSecondaryDictionaryField);
        secondaryEducationField.setStatus(true);

        List<CvApplicationField> applicationFieldListForEducationalSection = new ArrayList<CvApplicationField>();
        applicationFieldListForEducationalSection.add(primaryEducationField);
        applicationFieldListForEducationalSection.add(secondaryEducationField);
        educationalQualificationSection.setCvApplicationFieldList(applicationFieldListForEducationalSection);

        //project experience section
        CvApplicationField projectExperienceField = new CvApplicationField();
        projectExperienceField.setId("project_5");
        projectExperienceField.setApplicationFieldDictionary(projectWorksDictionaryField);
        projectExperienceField.setStatus(true);

        List<CvApplicationField> applicationFieldListProjectExperienceSection = new ArrayList<CvApplicationField>();
        applicationFieldListProjectExperienceSection.add(projectExperienceField);
        projectWorkSection.setCvApplicationFieldList(applicationFieldListProjectExperienceSection);

        //now we will create new cv application template with given data
        cvApplicationTemplateDao.create(cvApplicationTemplate);
        Assert.assertNotNull(cvApplicationTemplate);
        Assert.assertNotNull(cvApplicationTemplate.getId());
        Assert.assertEquals(cvApplicationTemplate.getCvHeaderEn(),"Application for Trainee Software Engineer");
        Assert.assertEquals(cvApplicationTemplate.getCvHeaderSi(),"Application for Trainee Software Engineer_SI");
        Assert.assertEquals(cvApplicationTemplate.getCvHeaderTa(),"Application for Trainee Software Engineer_TA");
        Assert.assertTrue(cvApplicationTemplate.getStatus());

        Assert.assertNotNull(cvApplicationTemplate.getCvApplicationSectionList());
        List<CvApplicationSection> cvApplicationSectionList = cvApplicationTemplate.getCvApplicationSectionList();
        for(int cvApplicationIndex=0;cvApplicationIndex<cvApplicationSectionList.size();cvApplicationIndex++){
            Assert.assertNotNull(cvApplicationTemplate.getCvApplicationSectionList().get(cvApplicationIndex));
        }

        CvApplicationTemplate cvApplicationTemplateFound  = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNotNull(cvApplicationTemplateFound);
        Assert.assertNotNull(cvApplicationTemplateFound.getId());
        Assert.assertEquals(cvApplicationTemplateFound.getCvHeaderEn(),"Application for Trainee Software Engineer");
        Assert.assertEquals(cvApplicationTemplateFound.getCvHeaderSi(),"Application for Trainee Software Engineer_SI");
        Assert.assertEquals(cvApplicationTemplateFound.getCvHeaderTa(),"Application for Trainee Software Engineer_TA");
        Assert.assertTrue(cvApplicationTemplateFound.getStatus());
        Assert.assertNotNull(cvApplicationTemplateFound.getCvApplicationSectionList());
        List<CvApplicationSection> cvApplicationSectionListFound = cvApplicationTemplateFound.getCvApplicationSectionList();
        for(int cvApplicationIndex=0;cvApplicationIndex<cvApplicationSectionListFound.size();cvApplicationIndex++){
            Assert.assertNotNull(cvApplicationTemplateFound.getCvApplicationSectionList().get(cvApplicationIndex));
        }

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationTemplateDao.removeCvTemplate(cvApplicationTemplate);
        CvApplicationTemplate cvApplicationTemplate1 = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNull(cvApplicationTemplate1);
    }


    @Test
    public void updateCvTemplate(){
        //creating cv template
        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeaderEn("Application for Software Engineer");
        cvApplicationTemplate.setCvHeaderSi("Application for Software Engineer_SI");
        cvApplicationTemplate.setCvHeaderTa("Application for Software Engineer_TA");
        cvApplicationTemplate.setStatus(true);

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
        ApplicationFieldDictionary nameDictionaryField = createTextFieldDictionary("name","nameSi","nameTa",60);
        nameDictionaryField.setId("1");
        ApplicationFieldDictionary ageDictionaryField = createTextFieldDictionary("age","ageSi","ageTa",10);
        ageDictionaryField.setId("2");
        ApplicationFieldDictionary educationalPrimaryDictionaryField = createTextAreaDictionary("primary education","primary educationSi","primary educationTa", 10, 20);
        educationalPrimaryDictionaryField.setId("3");
        ApplicationFieldDictionary educationalSecondaryDictionaryField = createTextAreaDictionary("secondary education","secondary educationSi","secondary educationTa", 10, 20);
        educationalSecondaryDictionaryField.setId("4");
        ApplicationFieldDictionary projectWorksDictionaryField = createTextAreaDictionary("Work Experience","Work ExperienceSi","Work ExperienceTa",15,30);
        projectWorksDictionaryField.setId("5");

        //now we will create each application field with priority and assign them for the cv application sections
        // we will start with personal details section. there are two fields known as name and age
        CvApplicationField nameField = new CvApplicationField();
        nameField.setId("name_1");
        nameField.setPriority(1);
        nameField.setApplicationFieldDictionary(nameDictionaryField);
        nameField.setStatus(true);

        CvApplicationField ageField = new CvApplicationField();
        ageField.setId("age_2");
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
        primaryEducationField.setId("primary_3");
        primaryEducationField.setPriority(1);
        primaryEducationField.setApplicationFieldDictionary(educationalPrimaryDictionaryField);
        primaryEducationField.setStatus(true);

        CvApplicationField secondaryEducationField = new CvApplicationField();
        secondaryEducationField.setId("secondary_4");
        secondaryEducationField.setPriority(2);
        secondaryEducationField.setApplicationFieldDictionary(educationalSecondaryDictionaryField);
        secondaryEducationField.setStatus(true);

        List<CvApplicationField> applicationFieldListForEducationalSection = new ArrayList<CvApplicationField>();
        applicationFieldListForEducationalSection.add(primaryEducationField);
        applicationFieldListForEducationalSection.add(secondaryEducationField);
        educationalQualificationSection.setCvApplicationFieldList(applicationFieldListForEducationalSection);

        //project experience section
        CvApplicationField projectExperienceField = new CvApplicationField();
        projectExperienceField.setId("project_5");
        projectExperienceField.setApplicationFieldDictionary(projectWorksDictionaryField);
        projectExperienceField.setStatus(true);

        List<CvApplicationField> applicationFieldListProjectExperienceSection = new ArrayList<CvApplicationField>();
        applicationFieldListProjectExperienceSection.add(projectExperienceField);
        projectWorkSection.setCvApplicationFieldList(applicationFieldListProjectExperienceSection);

        //now we will create new cv application template with given data
        cvApplicationTemplateDao.update(cvApplicationTemplate);
        Assert.assertNotNull(cvApplicationTemplate);
        Assert.assertNotNull(cvApplicationTemplate.getId());
        Assert.assertEquals(cvApplicationTemplate.getCvHeaderEn(),"Application for Software Engineer");
        Assert.assertEquals(cvApplicationTemplate.getCvHeaderSi(),"Application for Software Engineer_SI");
        Assert.assertEquals(cvApplicationTemplate.getCvHeaderTa(),"Application for Software Engineer_TA");
        Assert.assertTrue(cvApplicationTemplate.getStatus());

        Assert.assertNotNull(cvApplicationTemplate.getCvApplicationSectionList());
        List<CvApplicationSection> cvApplicationSectionList = cvApplicationTemplate.getCvApplicationSectionList();
        for(int cvApplicationIndex=0;cvApplicationIndex<cvApplicationSectionList.size();cvApplicationIndex++){
            Assert.assertNotNull(cvApplicationTemplate.getCvApplicationSectionList().get(cvApplicationIndex));
        }

        CvApplicationTemplate cvApplicationTemplateFound  = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNotNull(cvApplicationTemplateFound);
        Assert.assertNotNull(cvApplicationTemplateFound.getId());
        Assert.assertEquals(cvApplicationTemplateFound.getCvHeaderEn(),"Application for Software Engineer");
        Assert.assertEquals(cvApplicationTemplateFound.getCvHeaderSi(),"Application for Software Engineer_SI");
        Assert.assertEquals(cvApplicationTemplateFound.getCvHeaderTa(),"Application for Software Engineer_TA");
        Assert.assertTrue(cvApplicationTemplateFound.getStatus());
        Assert.assertNotNull(cvApplicationTemplateFound.getCvApplicationSectionList());
        List<CvApplicationSection> cvApplicationSectionListFound = cvApplicationTemplateFound.getCvApplicationSectionList();
        for(int cvApplicationIndex=0;cvApplicationIndex<cvApplicationSectionListFound.size();cvApplicationIndex++){
            Assert.assertNotNull(cvApplicationTemplateFound.getCvApplicationSectionList().get(cvApplicationIndex));
        }

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationTemplateDao.removeCvTemplate(cvApplicationTemplate);
        CvApplicationTemplate cvApplicationTemplate1 = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNull(cvApplicationTemplate1);
    }


    /**
     * <p>
     *     create application template sections
     * </p>
     * @param nameEn
     * @param nameSi
     * @param nameTa
     * @param id
     * @param status
     * @return
     */
    private CvApplicationSection createCvApplicationSection(String nameEn, String nameSi, String nameTa,String id,boolean status){
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setId(id);
        cvApplicationSection.setSectionNameEn(nameEn);
        cvApplicationSection.setSectionNameSi(nameSi);
        cvApplicationSection.setSectionNameTa(nameTa);
        cvApplicationSection.setStatus(status);
        return cvApplicationSection;
    }

    /**
     * <p>
     *     create application template section field dictionary
     * </p>
     * @param labelEn
     * @param labelSi
     * @param labelTa
     * @param size
     * @return
     */
    private TextFieldDictionary createTextFieldDictionary(String labelEn, String labelSi, String labelTa,Integer size){
        TextFieldDictionary textFieldDictionary = new TextFieldDictionary();
        textFieldDictionary.setLabelEn(labelEn);
        textFieldDictionary.setLabelSi(labelSi);
        textFieldDictionary.setLabelTa(labelTa);
        textFieldDictionary.setHtmlComponent("TextField");
        textFieldDictionary.setSize(size);
        textFieldDictionary.setStatus(true);
        return textFieldDictionary;
    }

    /**
     * <p>
     *     create application template section field dictionary
     * </p>
     * @param labelEn
     * @param labelSi
     * @param labelTa
     * @param rows
     * @param cols
     * @return
     */
    private TextAreaDictionary createTextAreaDictionary(String labelEn, String labelSi,String labelTa, Integer rows,Integer cols){
        TextAreaDictionary textAreaDictionary = new TextAreaDictionary();
        textAreaDictionary.setLabelEn(labelEn);
        textAreaDictionary.setLabelSi(labelSi);
        textAreaDictionary.setLabelTa(labelTa);
        textAreaDictionary.setHtmlComponent("TextArea");
        textAreaDictionary.setRows(rows);
        textAreaDictionary.setCols(cols);
        textAreaDictionary.setStatus(true);
        return textAreaDictionary;
    }

}
