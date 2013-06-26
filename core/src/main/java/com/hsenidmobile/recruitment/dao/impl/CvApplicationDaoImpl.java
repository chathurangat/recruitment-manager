package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CvApplicationDao;
import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.model.CvApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cvApplicationDao")
public class CvApplicationDaoImpl implements CvApplicationDao{

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "cv_application";

     /** {@inheritDoc} */
    @Override
    public String create(CvApplication cvApplication) {
        if (!mongoTemplate.collectionExists(CvApplication.class)) {
            mongoTemplate.createCollection(CvApplication.class);
        }
        mongoTemplate.insert(cvApplication, COLLECTION_NAME);
        return cvApplication.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove(CvApplication cvApplication) {
        mongoTemplate.remove(cvApplication,COLLECTION_NAME);
    }

    /** {@inheritDoc} */
    @Override
    public CvApplication findById(String applicationId) {
        return mongoTemplate.findById(applicationId,CvApplication.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<CvApplication> findCvApplicationsByApplicant(Applicant applicant) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userApplied._id").is(applicant.getId()));
        return mongoTemplate.find(query, CvApplication.class);
    }
}
