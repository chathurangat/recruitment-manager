package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.ApplicantDao;
import com.hsenidmobile.recruitment.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository("applicantDao")
public class ApplicantDaoImpl implements ApplicantDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "applicant";


    /**
     * {@inheritDoc}
     */
    public void addApplicant(Applicant applicant) {
        if (!mongoTemplate.collectionExists(Applicant.class)) {
            mongoTemplate.createCollection(Applicant.class);
        }
        mongoTemplate.insert(applicant, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    public void removeApplicant(Applicant applicant){
        mongoTemplate.remove(applicant,COLLECTION_NAME);
    }


    /**
     * {@inheritDoc}
     */
    public Applicant findApplicantById(Long id){
        return mongoTemplate.findById(id,Applicant.class);
    }
}
