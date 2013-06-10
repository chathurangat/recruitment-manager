package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationTemplateDao;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import junit.framework.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class CvApplicationTemplateDaoImplTest extends CommonDaoTest{

    @Autowired
    private CvApplicationTemplateDao cvApplicationTemplateDao;

    @Test
    public void createCvTemplate(){

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
    }


    @Test
    public void updateCvTemplate(){

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
    }



    private CvApplicationSection createCvApplicationSection(String name,String id){
        CvApplicationSection cvApplicationSection = new CvApplicationSection();
        cvApplicationSection.setId(id);
        cvApplicationSection.setSectionName(name);
        return cvApplicationSection;
    }
}
