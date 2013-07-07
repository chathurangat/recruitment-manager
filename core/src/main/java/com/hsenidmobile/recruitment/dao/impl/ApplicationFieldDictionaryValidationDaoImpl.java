package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.ApplicationFieldDictionaryValidationDao;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionaryValidation;
import com.hsenidmobile.recruitment.model.CvApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("applicationFieldDictionaryValidationDao")
public class ApplicationFieldDictionaryValidationDaoImpl implements ApplicationFieldDictionaryValidationDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_field_validations_criteria";

    @Override
    public String create(ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation) {
        if (!mongoTemplate.collectionExists(CvApplication.class)) {
            mongoTemplate.createCollection(CvApplication.class);
        }
        mongoTemplate.insert(applicationFieldDictionaryValidation, COLLECTION_NAME);
        return applicationFieldDictionaryValidation.getId();
    }

    @Override
    public ApplicationFieldDictionaryValidation findValidationById(String Id) {
        return mongoTemplate.findById(Id,ApplicationFieldDictionaryValidation.class);
    }

    @Override
    public void remove(ApplicationFieldDictionaryValidation applicationFieldDictionaryValidation) {
        mongoTemplate.remove(applicationFieldDictionaryValidation,COLLECTION_NAME);
    }

    @Override
    public List<ApplicationFieldDictionaryValidation> getAllValidationCriteria() {
        return mongoTemplate.findAll(ApplicationFieldDictionaryValidation.class);
    }
}
