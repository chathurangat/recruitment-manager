package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationTemplateDao;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CvApplicationTemplateDaoTest extends CommonDaoTest{

    @Autowired
    private CvApplicationTemplateDao cvApplicationTemplateDao;

    @Test
    public void create(){
        CvApplicationTemplate cvApplicationTemplate = new CvApplicationTemplate();
        cvApplicationTemplate.setCvHeaderEn("header_English");
        cvApplicationTemplate.setCvHeaderSi("header Sinhaha");
        cvApplicationTemplate.setCvHeaderTa("header Tamil");
        cvApplicationTemplate.setStatus(true);
        cvApplicationTemplate.setCvApplicationSectionList(null);
       cvApplicationTemplateDao.create(cvApplicationTemplate);

        CvApplicationTemplate cvApplicationTemplate1 = cvApplicationTemplateDao.findCvTemplateById(cvApplicationTemplate.getId());
        Assert.assertNotNull(cvApplicationTemplate1);
        System.out.println(" cv "+cvApplicationTemplate1.getId());
    }
}


