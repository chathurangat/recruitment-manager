package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationFieldDictionaryDao;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import com.hsenidmobile.recruitment.service.CvApplicationFieldDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("cvApplicationFieldDictionaryService")
public class CvApplicationFieldDictionaryServiceImpl implements CvApplicationFieldDictionaryService {

    @Autowired
    private CvApplicationFieldDictionaryDao  cvApplicationFieldDictionaryDao;

    @Override
    public void createCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary) {
        cvApplicationFieldDictionaryDao.createCvSectionFieldDictionary(applicationFieldDictionary);
    }

    @Override
    public void updateCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary) {
        cvApplicationFieldDictionaryDao.updateCvSectionFieldDictionary(applicationFieldDictionary);
    }

    @Override
    public void removeCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary) {
        cvApplicationFieldDictionaryDao.removeCvSectionFieldDictionary(applicationFieldDictionary);
    }

    @Override
    public ApplicationFieldDictionary findCvSectionFieldDictionaryById(String id) {
        return cvApplicationFieldDictionaryDao.findCvSectionFieldDictionaryById(id);
    }

    @Override
    public List<ApplicationFieldDictionary> findAllCvSectionFieldDictionary(){
        return cvApplicationFieldDictionaryDao.findAllCvSectionFieldDictionary();
    }
}