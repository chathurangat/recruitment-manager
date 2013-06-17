package com.hsenidmobile.recruitment.web.service.impl;

import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.web.dao.CvSectionInsertDao;
import com.hsenidmobile.recruitment.web.model.CvSectionInsert;
import com.hsenidmobile.recruitment.web.service.CvSectionInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 6/17/13
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */

@Service("cvSectionInsertService")
public class CvSectionInsertServiceImpl implements CvSectionInsertService{
    @Autowired
    private CvSectionInsertDao cvSectionInsertDao;

    @Override
    public void addCvSection(CvSectionInsert cvSectionInsert) {
        cvSectionInsertDao.addCvSection(cvSectionInsert);
    }

    @Override
    public void removeCvSection(CvSectionInsert cvSectionInsert) {
        cvSectionInsertDao.removeCvSection(cvSectionInsert);
    }


    @Override
    public CvSectionInsert findCvSectionById(String id) {
        return cvSectionInsertDao.findCvsectionById(id);
    }

}
