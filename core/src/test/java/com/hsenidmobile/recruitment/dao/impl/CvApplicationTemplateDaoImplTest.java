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
        cvApplicationTemplate.setCvHeader("Application for Trainee Software Engineer");

        // creating sections for the cv template
        CvApplicationSection personalDetailsSection = createCvApplicationSection("personal details","1",true);
        CvApplicationSection educationalQualificationSection = createCvApplicationSection("educational qualifications","2",true);
        CvApplicationSection projectWorkSection = createCvApplicationSection("project works","3",true);

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

        //now we will create new cv application template with given data
        cvApplicationTemplateDao.create(cvApplicationTemplate);
        Assert.assertNotNull(cvApplicationTemplate.getId());

        CvApplicationTemplate cvApplicationTemplateFound  = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNotNull(cvApplicationTemplateFound);

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        cvApplicationTemplateDao.removeCvTemplate(cvApplicationTemplate);
        CvApplicationTemplate cvApplicationTemplate1 = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNull(cvApplicationTemplate1);
    }


    @Test
    public void updateCvTemplate(){
        //todo write updateCvTemplate ->nilaxan, piyumie

    }


    /**
     * <p>
     *     create application template sections
     * </p>
     * @param name
     * @param id
     * @param status
     * @return
     */
    private CvApplicationSection createCvApplicationSection(String name,String id,boolean status){
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setId(id);
        cvApplicationSection.setSectionName(name);
        cvApplicationSection.setStatus(status);
        return cvApplicationSection;
    }


    private TextFieldDictionary createTextFieldDictionary(String label,Integer size){
        TextFieldDictionary textFieldDictionary = new TextFieldDictionary();
        textFieldDictionary.setLabel(label);
        textFieldDictionary.setHtmlComponent("TextField");
        textFieldDictionary.setSize(size);
        textFieldDictionary.setStatus(true);
        return textFieldDictionary;
    }

    private TextAreaDictionary createTextAreaDictionary(String label,Integer rows,Integer cols){
        TextAreaDictionary textAreaDictionary = new TextAreaDictionary();
        textAreaDictionary.setLabel(label);
        textAreaDictionary.setHtmlComponent("TextArea");
        textAreaDictionary.setRows(rows);
        textAreaDictionary.setCols(cols);
        textAreaDictionary.setStatus(true);
        return textAreaDictionary;
    }

}
