package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.model.CvApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        Assert.assertNotNull(applicant.getId());

        //select applicant by id
        Applicant applicant1 = applicantDao.findApplicantById(applicant.getId());
        Assert.assertNotNull(applicant1);
        Assert.assertNotNull(applicant1.getId());

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

        Assert.assertNotNull(applicant.getId());

        Applicant applicantFound = applicantDao.findApplicantFromSocialNetworkDetails("facebook","chathuranga.tennakoon","123456",true);
        Assert.assertNotNull(applicantFound);
        Assert.assertNotNull(applicantFound.getId());

        //removing the applicant
        applicantDao.removeApplicant(applicant);
        Applicant applicant2 = applicantDao.findApplicantById(applicant.getId());
        Assert.assertNull(applicant2);
    }

}

