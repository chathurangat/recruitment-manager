package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationSectionDao;
import com.hsenidmobile.recruitment.model.CvApplicationSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository("cvApplicationSectionDao")
public class CvApplicationSectionDaoImpl implements CvApplicationSectionDao{

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_register";


    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CvApplicationSection cvApplicationSection) {
        if (!mongoTemplate.collectionExists(CvApplicationSection.class)) {
            mongoTemplate.createCollection(CvApplicationSection.class);
        }
        mongoTemplate.insert(cvApplicationSection, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CvApplicationSection cvApplicationSection){
        mongoTemplate.save(cvApplicationSection,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCvTemplate(CvApplicationSection cvApplicationSection) {
        mongoTemplate.remove(cvApplicationSection,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CvApplicationSection findCvSectionById(String id) {
        return mongoTemplate.findById(id,CvApplicationSection.class);
    }
}
