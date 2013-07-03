package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationTemplateDao;
import com.hsenidmobile.recruitment.model.CvApplicationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cvApplicationTemplateDao")
public class CvApplicationTemplateDaoImpl implements CvApplicationTemplateDao{

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_template";


    /**
     * {@inheritDoc}
     */
    @Override
    public void create(CvApplicationTemplate cvApplicationTemplate) {
        if (!mongoTemplate.collectionExists(CvApplicationTemplate.class)) {
            mongoTemplate.createCollection(CvApplicationTemplate.class);
        }
        mongoTemplate.insert(cvApplicationTemplate, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(CvApplicationTemplate cvApplicationTemplate){
        mongoTemplate.save(cvApplicationTemplate,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCvTemplate(CvApplicationTemplate cvApplicationTemplate) {
        mongoTemplate.remove(cvApplicationTemplate,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CvApplicationTemplate findCvTemplateById(String id) {
        return mongoTemplate.findById(id,CvApplicationTemplate.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CvApplicationTemplate> findAllCvTemplate() {
        return mongoTemplate.findAll(CvApplicationTemplate.class);
    }
}
