package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.ApplicantDao;
import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("applicantService")
public class ApplicantServiceImpl implements ApplicantService{

    @Autowired
    private ApplicantDao applicantDao;

    @Override
    public void addApplicant(Applicant applicant) {
        applicantDao.addApplicant(applicant);
    }

    @Override
    public void removeApplicant(Applicant applicant) {
        applicantDao.removeApplicant(applicant);
    }

    @Override
    public Applicant findApplicantById(String id) {
        return applicantDao.findApplicantById(id);
    }

    @Override
    public Applicant findApplicantFromSocialNetworkDetails(String socialNetwork, String username, String socialNetworkUserId, boolean status) {
        return applicantDao.findApplicantFromSocialNetworkDetails(socialNetwork,username,socialNetworkUserId,status);
    }

    @Override
    public Applicant findApplicantFromSocialNetworkDetails(String socialNetwork, String username, boolean status) {
        return applicantDao.findApplicantFromSocialNetworkDetails(socialNetwork,username,status);
    }
}
