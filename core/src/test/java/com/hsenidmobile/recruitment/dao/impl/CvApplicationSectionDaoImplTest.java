package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.CvApplicationSectionDao;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 6/18/13
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
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


        //now we will create new cv application section with given data
        cvApplicationSectionDao.create(cvApplicationSection);
        Assert.assertNotNull(cvApplicationSection.getId());

        List<CvApplicationSection> cvApplicationSectionFound  = cvApplicationSectionDao.findAllCvSection();
        Assert.assertNotNull(cvApplicationSectionFound);

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
    /*    cvApplicationSectionDao.removeCvSection(cvApplicationSection);
        CvApplicationSection cvApplicationSection1 = cvApplicationSectionDao.findCvSectionById(cvApplicationSection.getId());
        Assert.assertNull(cvApplicationSection1);
        */

    }

    @Test
    public void  updateCvSection() {
       //todo write update CvApplicationSection
    }
}
