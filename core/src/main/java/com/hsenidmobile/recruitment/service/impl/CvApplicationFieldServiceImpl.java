package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationFieldDao;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import com.hsenidmobile.recruitment.service.CvApplicationFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("cvApplicationFieldService")
public class CvApplicationFieldServiceImpl implements CvApplicationFieldService {

    @Autowired
    private CvApplicationFieldDao cvApplicationFieldDao;

    @Override
    public void createCvSectionField(CvApplicationField cvApplicationField) {
        cvApplicationFieldDao.createCvSectionField(cvApplicationField);
    }

    @Override
    public void updateCvSectionField(CvApplicationField cvApplicationField) {
        cvApplicationFieldDao.updateCvSectionField(cvApplicationField);
    }

    @Override
    public void removeCvSectionField(CvApplicationField cvApplicationField) {
        cvApplicationFieldDao.removeCvSectionField(cvApplicationField);
    }

    @Override
    public CvApplicationField findCvSectionFieldById(String id) {
        return cvApplicationFieldDao.findCvSectionFieldById(id);
    }

    @Override
    public List<CvApplicationField> findAllCvSectionField(){
        return cvApplicationFieldDao.findAllCvSectionField();
    }
}