package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationFieldDictionaryDao;
import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("cvApplicationFieldDictionaryDao")
public class CvApplicationFieldDictionaryDaoImpl implements CvApplicationFieldDictionaryDao {

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_field_dictionary_register";


    /**
     * {@inheritDoc}
     */
    @Override
    public void createCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary) {
        if (!mongoTemplate.collectionExists(ApplicationFieldDictionary.class)) {
            mongoTemplate.createCollection(ApplicationFieldDictionary.class);
        }
        mongoTemplate.insert(applicationFieldDictionary, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary){
        mongoTemplate.save(applicationFieldDictionary,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary) {
        mongoTemplate.remove(applicationFieldDictionary,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationFieldDictionary findCvSectionFieldDictionaryById(String id) {
        return mongoTemplate.findById(id,ApplicationFieldDictionary.class,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ApplicationFieldDictionary> findAllCvSectionFieldDictionary() {
        return mongoTemplate.findAll(ApplicationFieldDictionary.class);
    }
}
