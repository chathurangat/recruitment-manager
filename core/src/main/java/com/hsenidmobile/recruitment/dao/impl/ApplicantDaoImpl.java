package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.ApplicantDao;
import com.hsenidmobile.recruitment.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public Applicant findApplicantById(String id){
        return mongoTemplate.findById(id,Applicant.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Applicant findApplicantFromSocialNetworkDetails(String socialNetwork, String username, String socialNetworkUserId,boolean status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("socialNetworkResourceId").is(socialNetworkUserId).and("username").is(username).and("status").exists(status));
        return mongoTemplate.findOne(query, Applicant.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Applicant findApplicantFromSocialNetworkDetails(String socialNetwork, String username,boolean status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username).and("status").exists(status));
        return mongoTemplate.findOne(query, Applicant.class);
    }
}
