package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.VacancyDao;
import com.hsenidmobile.recruitment.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/11/13
 * Time: 1:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("vacancyDao")
public class VacancyDaoImpl implements VacancyDao {
    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "vacancy_publish";

    @Override
    public void create(Vacancy vacancy){
        if (!mongoTemplate.collectionExists(Vacancy.class)) {
            mongoTemplate.createCollection(Vacancy.class);
        }
        mongoTemplate.insert(vacancy, COLLECTION_NAME);
    }
    @Override
    public void update(Vacancy vacancy){
        mongoTemplate.save(vacancy,COLLECTION_NAME);
    }



    @Override
    public void removeVacancy(Vacancy vacancy) {
        mongoTemplate.remove(vacancy,COLLECTION_NAME);
    }

    @Override
    public Vacancy findVacancyById(String id) {
        return mongoTemplate.findById(id,Vacancy.class,COLLECTION_NAME);
    }
    @Override
    public List<Vacancy> findAllVacancy() {
        return mongoTemplate.findAll(Vacancy.class);
    }


}
