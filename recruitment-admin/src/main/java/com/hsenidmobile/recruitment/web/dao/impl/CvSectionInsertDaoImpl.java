package com.hsenidmobile.recruitment.web.dao.impl;

import com.hsenidmobile.recruitment.web.dao.CvSectionInsertDao;
import com.hsenidmobile.recruitment.web.model.CvSectionInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 6/17/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("cvSectionInsertDao")
public class CvSectionInsertDaoImpl implements CvSectionInsertDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_register";

    /**
     * {@inheritDoc}
     */
    public void addCvSection(CvSectionInsert cvSectionInsert) {
        if (!mongoTemplate.collectionExists(CvSectionInsert.class)) {
            mongoTemplate.createCollection(CvSectionInsert.class);
        }
        mongoTemplate.insert(cvSectionInsert, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    public void removeCvSection(CvSectionInsert cvSectionInsert){
        mongoTemplate.remove(cvSectionInsert,COLLECTION_NAME);
    }


    /**
     * {@inheritDoc}
     */
    public CvSectionInsert findCvSectionById(String id){
        return mongoTemplate.findById(id,CvSectionInsert.class);
    }
}
