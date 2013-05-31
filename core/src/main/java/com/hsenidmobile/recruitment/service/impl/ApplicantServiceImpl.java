package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.ApplicantDao;
import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicantServiceImpl implements ApplicantService{

    @Autowired
    private ApplicantDao applicantDao;

    @Override
    public void addApplicant(Applicant applicant) {
        applicantDao.addApplicant(applicant);
    }
}
