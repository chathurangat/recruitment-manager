package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.model.CvApplication;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ApplicantDaoTest extends CommonDaoTest{

    @Autowired
    private ApplicantDao applicantDao;

    @Test
    public void testCreateApplicant(){
        //create new applicant
        Applicant applicant =  new Applicant();
        applicant.setApplicantName("chathuranga tennakoon");

        //add application one
        CvApplication cvApplication = new CvApplication();
        cvApplication.setApplicationName("app_10");
        applicant.addCvApplication(cvApplication);

        //add application two
        CvApplication cvApplication1 = new CvApplication();
        cvApplication1.setApplicationName("app_12");
        applicant.addCvApplication(cvApplication1);

        applicantDao.addApplicant(applicant);
        Assert.assertNotNull(applicant);
        Assert.assertNotNull(applicant.getId());
        Assert.assertEquals(applicant.getApplicantName(), "chathuranga tennakoon");
        Assert.assertNotNull(applicant.getCvApplicationList());

        //select applicant by id
        Applicant applicant1 = applicantDao.findApplicantById(applicant.getId());
        Assert.assertNotNull(applicant1);
        Assert.assertNotNull(applicant1.getId());
        Assert.assertEquals(applicant1.getApplicantName(),"chathuranga tennakoon");
        Assert.assertNotNull(applicant.getCvApplicationList());

        //removing the applicant
        applicantDao.removeApplicant(applicant);
        Applicant applicant2 = applicantDao.findApplicantById(applicant.getId());
        Assert.assertNull(applicant2);

    }


    @Test
    public void testFindApplicantFromSocialNetworkDetails(){
        Applicant applicant = new Applicant();
        applicant.setApplicantName("chathuranga");
        applicant.setUsername("chathuranga.tennakoon");
        applicant.setSocialNetworkResourceId("123456");
        applicant.setOpenIdProvider("facebook");
        applicant.setStatus(true);
        applicantDao.addApplicant(applicant);

        Assert.assertNotNull(applicant);
        Assert.assertNotNull(applicant.getId());
        Assert.assertEquals(applicant.getApplicantName(),"chathuranga");
        Assert.assertEquals(applicant.getUsername(),"chathuranga.tennakoon");
        Assert.assertEquals(applicant.getSocialNetworkResourceId(),"123456");
        Assert.assertEquals(applicant.getOpenIdProvider(),"facebook");
        Assert.assertTrue(applicant.isStatus());

        Applicant applicantFound = applicantDao.findApplicantFromSocialNetworkDetails("facebook","chathuranga.tennakoon","123456",true);
        Assert.assertNotNull(applicantFound);
        Assert.assertNotNull(applicantFound.getId());
        Assert.assertEquals(applicantFound.getOpenIdProvider(),"facebook");
        Assert.assertEquals(applicantFound.getUsername(),"chathuranga.tennakoon");
        Assert.assertEquals(applicantFound.getSocialNetworkResourceId(),"123456");
        Assert.assertTrue(applicantFound.isStatus());

        //removing the applicant
        applicantDao.removeApplicant(applicant);
        Applicant applicant2 = applicantDao.findApplicantById(applicant.getId());
        Assert.assertNull(applicant2);

    }

}

