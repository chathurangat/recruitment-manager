package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationSectionDao;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import com.hsenidmobile.recruitment.service.CvApplicationSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


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
    public void removeCvSection(CvApplicationSection cvApplicationSection) {
        cvApplicationSectionDao.removeCvSection(cvApplicationSection);
    }

    @Override
    public CvApplicationSection findCvSectionById(String id) {
        return cvApplicationSectionDao.findCvSectionById(id);
    }

    @Override
    public List<CvApplicationSection> findAllCvSection(){
        return cvApplicationSectionDao.findAllCvSection();
    }
}