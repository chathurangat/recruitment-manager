package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationSectionDao;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: nilaxan
 * Date: 6/17/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("cvApplicationSectionService")
public class CvApplicationSectionServiceImpl implements CvApplicationSectionService{

    @Autowired
    private CvApplicationSectionDao cvApplicationSectionDao;

    @Override
    public void create(CvApplicationSection cvApplicationSection) {
        cvApplicationSectionDao.create(cvApplicationSection);
    }

    @Override
    public void update(CvApplicationSection cvApplicationSection) {
       cvApplicationSectionDao.update(cvApplicationSection);
    }

    @Override
    public void removeCvTemplate(CvApplicationSection cvApplicationSection) {
        cvApplicationSectionDao.removeCvTemplate(cvApplicationSection);
    }

    @Override
    public CvApplicationSection findCvSectionById(String id) {
        return cvApplicationSectionDao.findCvSectionById(id);
    }
}