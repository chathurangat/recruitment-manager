package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationTemplateDao;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import com.hsenidmobile.recruitment.service.CvApplicationTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cvApplicationTemplateService")
public class CvApplicationTemplateServiceImpl implements CvApplicationTemplateService{

    @Autowired
    private CvApplicationTemplateDao cvApplicationTemplateDao;

    @Override
    public void create(CvApplicationTemplate cvApplicationTemplate) {
        cvApplicationTemplateDao.create(cvApplicationTemplate);
    }

    @Override
    public void update(CvApplicationTemplate cvApplicationTemplate) {
        cvApplicationTemplateDao.update(cvApplicationTemplate);
    }

    @Override
    public void removeCvTemplate(CvApplicationTemplate cvApplicationTemplate) {
        cvApplicationTemplateDao.removeCvTemplate(cvApplicationTemplate);
    }

    @Override
    public CvApplicationTemplate findCvTemplateById(String id) {
        return cvApplicationTemplateDao.findCvTemplateById(id);
    }
}
