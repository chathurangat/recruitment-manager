package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationFieldDao;
import com.hsenidmobile.recruitment.model.CvApplicationField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("cvApplicationFieldDao")
public class CvApplicationFieldDaoImpl implements CvApplicationFieldDao {

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_field_register";


    /**
     * {@inheritDoc}
     */
    @Override
    public void createCvSectionField(CvApplicationField cvApplicationField) {
        if (!mongoTemplate.collectionExists(CvApplicationField.class)) {
            mongoTemplate.createCollection(CvApplicationField.class);
        }
        mongoTemplate.insert(cvApplicationField, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCvSectionField(CvApplicationField cvApplicationField){
        mongoTemplate.save(cvApplicationField,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCvSectionField(CvApplicationField cvApplicationField) {
        mongoTemplate.remove(cvApplicationField,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CvApplicationField findCvSectionFieldById(String id) {
        return mongoTemplate.findById(id,CvApplicationField.class,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CvApplicationField> findAllCvSectionField() {
        return mongoTemplate.findAll(CvApplicationField.class);
    }
}
