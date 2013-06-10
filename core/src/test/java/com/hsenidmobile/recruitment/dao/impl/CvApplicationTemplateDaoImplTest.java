package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationTemplateDao;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
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

        // Application for Trainee Software Engineer
        CvApplicationSection cvApplicationSection1 = createCvApplicationSection("personal details","1",true);
        CvApplicationSection cvApplicationSection2 = createCvApplicationSection("career objective","2",true);
        CvApplicationSection cvApplicationSection3 = createCvApplicationSection("software proficiency","3",true);
        CvApplicationSection cvApplicationSection4 = createCvApplicationSection("educational qualifications","4",true);
        CvApplicationSection cvApplicationSection5 = createCvApplicationSection("project works","5",true);

        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeader("Application for Trainee Software Engineer");

        //adding the cv application sections
        List<CvApplicationSection> cvApplicationSections = new ArrayList<CvApplicationSection>();
        cvApplicationSections.add(cvApplicationSection1);
        cvApplicationSections.add(cvApplicationSection2);
        cvApplicationSections.add(cvApplicationSection3);
        cvApplicationSections.add(cvApplicationSection4);
        cvApplicationSections.add(cvApplicationSection5);

        //adding the application fields dictionary to section 1
        ApplicationFieldDictionary applicationFieldDictionarySection1_1 = createCvApplicationFieldDictionary(1,"textbox","full name",true);
        ApplicationFieldDictionary applicationFieldDictionarySection1_2 = createCvApplicationFieldDictionary(2,"textarea","address",true);
        ApplicationFieldDictionary applicationFieldDictionarySection1_3 = createCvApplicationFieldDictionary(3,"radiobox","sex",true);
        ApplicationFieldDictionary applicationFieldDictionarySection1_4 = createCvApplicationFieldDictionary(4,"textbox","mobile no",true);
        ApplicationFieldDictionary applicationFieldDictionarySection1_5 = createCvApplicationFieldDictionary(5,"textbox","e-mail",true);


        List<ApplicationFieldDictionary> applicationFieldDictionaryList1=new ArrayList<ApplicationFieldDictionary>();
        applicationFieldDictionaryList1.add(applicationFieldDictionarySection1_1);
        applicationFieldDictionaryList1.add(applicationFieldDictionarySection1_2);
        applicationFieldDictionaryList1.add(applicationFieldDictionarySection1_3);
        applicationFieldDictionaryList1.add(applicationFieldDictionarySection1_4);
        applicationFieldDictionaryList1.add(applicationFieldDictionarySection1_5);

        cvApplicationSection1.setApplicationFieldDictionaryList(applicationFieldDictionaryList1);

        //adding the application fields dictionary to section 2
        ApplicationFieldDictionary applicationFieldDictionarySection2_1 = createCvApplicationFieldDictionary(1,"textarea","career objective",true);
        List<ApplicationFieldDictionary> applicationFieldDictionaryList2 = new ArrayList<ApplicationFieldDictionary>();
        applicationFieldDictionaryList2.add(applicationFieldDictionarySection2_1);

        cvApplicationSection2.setApplicationFieldDictionaryList(applicationFieldDictionaryList2);

        //adding the application fields dictionary to section 3
        ApplicationFieldDictionary applicationFieldDictionarySection3_1 = createCvApplicationFieldDictionary(1,"checkbox","programming languages",true);
        ApplicationFieldDictionary applicationFieldDictionarySection3_2 = createCvApplicationFieldDictionary(2,"ckeckbox","software packages",true);
        ApplicationFieldDictionary applicationFieldDictionarySection3_3 = createCvApplicationFieldDictionary(3,"ckeckbox","operating systems",true);
        ApplicationFieldDictionary applicationFieldDictionarySection3_4 = createCvApplicationFieldDictionary(4,"ckeckbox","web techniques",true);
        ApplicationFieldDictionary applicationFieldDictionarySection3_5 = createCvApplicationFieldDictionary(5,"checkbox","databases",true);

        List<ApplicationFieldDictionary> applicationFieldDictionaryList3 = new ArrayList<ApplicationFieldDictionary>();
        applicationFieldDictionaryList3.add(applicationFieldDictionarySection3_1);
        applicationFieldDictionaryList3.add(applicationFieldDictionarySection3_2);
        applicationFieldDictionaryList3.add(applicationFieldDictionarySection3_3) ;
        applicationFieldDictionaryList3.add(applicationFieldDictionarySection3_4);
        applicationFieldDictionaryList3.add(applicationFieldDictionarySection3_5);

        cvApplicationSection3.setApplicationFieldDictionaryList(applicationFieldDictionaryList3);

        //adding the application fields dictionary to section 4
        ApplicationFieldDictionary applicationFieldDictionarySection4_1 = createCvApplicationFieldDictionary(1,"table","o/l result",true);
        ApplicationFieldDictionary applicationFieldDictionarySection4_2 = createCvApplicationFieldDictionary(2,"table","a/l",true);
        ApplicationFieldDictionary applicationFieldDictionarySection4_3 = createCvApplicationFieldDictionary(3,"table","diploma / degree",true);

        List<ApplicationFieldDictionary> applicationFieldDictionaryList4 = new ArrayList<ApplicationFieldDictionary>();
        applicationFieldDictionaryList4.add(applicationFieldDictionarySection4_1);
        applicationFieldDictionaryList4.add(applicationFieldDictionarySection4_2);
        applicationFieldDictionaryList4.add(applicationFieldDictionarySection4_3);

        cvApplicationSection4.setApplicationFieldDictionaryList(applicationFieldDictionaryList4);;

        //adding the application fields dictionary to section 5
        ApplicationFieldDictionary applicationFieldDictionarySection5_1 = createCvApplicationFieldDictionary(1,"table","project works",true);

        List<ApplicationFieldDictionary> applicationFieldDictionaryList5 = new ArrayList<ApplicationFieldDictionary>();
        applicationFieldDictionaryList5.add(applicationFieldDictionarySection5_1);

        cvApplicationSection5.setApplicationFieldDictionaryList(applicationFieldDictionaryList5);


        cvApplicationTemplate.setCvApplicationSectionList(cvApplicationSections);

        cvApplicationTemplateDao.create(cvApplicationTemplate);
        Assert.assertNotNull(cvApplicationTemplate.getId());


       /* Application for Senior Software Engineer
        CvApplicationSection cvApplicationSection1 = createCvApplicationSection("area of expertise","1",true);
        CvApplicationSection cvApplicationSection2 = createCvApplicationSection("professional experiences ","2",true);
        CvApplicationSection cvApplicationSection3 = createCvApplicationSection("personal skills","3",true);
        CvApplicationSection cvApplicationSection4 = createCvApplicationSection("educational qualifications","4",true);
        CvApplicationSection cvApplicationSection5 = createCvApplicationSection("personal details","5",true);

        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeader("Application for Senior Software Engineer");

        //adding the cv application sections
        List<CvApplicationSection> cvApplicationSections = new ArrayList<CvApplicationSection>();
        cvApplicationSections.add(cvApplicationSection1);
        cvApplicationSections.add(cvApplicationSection2);
        cvApplicationSections.add(cvApplicationSection3);
        cvApplicationSections.add(cvApplicationSection4);
        cvApplicationSections.add(cvApplicationSection5);

        cvApplicationTemplate.setCvApplicationSectionList(cvApplicationSections);

        cvApplicationTemplateDao.create(cvApplicationTemplate);

        Assert.assertNotNull(cvApplicationTemplate.getId());

        */


        /* Old Template Application for Software Engineer

        CvApplicationSection cvApplicationSection1 = createCvApplicationSection("personal details","1");
        CvApplicationSection cvApplicationSection2 = createCvApplicationSection("educational details","2");
        CvApplicationSection cvApplicationSection3 = createCvApplicationSection("work details","3");

        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeader("Application for Software Engineer");

        //adding the cv application sections
        List<CvApplicationSection> cvApplicationSections = new ArrayList<CvApplicationSection>();
        cvApplicationSections.add(cvApplicationSection1);
        cvApplicationSections.add(cvApplicationSection2);
        cvApplicationSections.add(cvApplicationSection3);

        cvApplicationTemplate.setCvApplicationSectionList(cvApplicationSections);

        cvApplicationTemplateDao.create(cvApplicationTemplate);

        Assert.assertNotNull(cvApplicationTemplate.getId());
        */
    }



    @Test
    public void updateCvTemplate(){
       /*
        CvApplicationSection cvApplicationSection1 = createCvApplicationSection("personal details","1");
        CvApplicationSection cvApplicationSection2 = createCvApplicationSection("educational details","2");
        CvApplicationSection cvApplicationSection3 = createCvApplicationSection("work details","3");

        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeader("Application for Software Engineer");

        //adding the cv application sections
        List<CvApplicationSection> cvApplicationSections = new ArrayList<CvApplicationSection>();
        cvApplicationSections.add(cvApplicationSection1);
        cvApplicationSections.add(cvApplicationSection2);
        cvApplicationSections.add(cvApplicationSection3);

        cvApplicationTemplate.setCvApplicationSectionList(cvApplicationSections);

        cvApplicationTemplateDao.create(cvApplicationTemplate);

        Assert.assertNotNull(cvApplicationTemplate.getId());

        cvApplicationTemplate.setCvHeader("Application for Senior Software Engineer");
        CvApplicationSection cvApplicationSection4 = createCvApplicationSection("self description","4");
        cvApplicationTemplate.getCvApplicationSectionList().add(cvApplicationSection4);

        cvApplicationTemplateDao.update(cvApplicationTemplate);
        */
    }




    private CvApplicationSection createCvApplicationSection(String name,String id,boolean status){
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setId(id);
        cvApplicationSection.setSectionName(name);
        cvApplicationSection.setStatus(status);
        return cvApplicationSection;
    }

    private ApplicationFieldDictionary createCvApplicationFieldDictionary(long id, String htmlComponent, String description, boolean status) {
        ApplicationFieldDictionary applicationFieldDictionary=new ApplicationFieldDictionary();
        applicationFieldDictionary.setId(id);
        applicationFieldDictionary.setHtmlComponent(htmlComponent);
        applicationFieldDictionary.setDescription(description);
        applicationFieldDictionary.setStatus(status);
        return applicationFieldDictionary;

    }

}
