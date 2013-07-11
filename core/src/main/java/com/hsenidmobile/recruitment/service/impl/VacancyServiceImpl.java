package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.VacancyDao;
import com.hsenidmobile.recruitment.model.Vacancy;
import com.hsenidmobile.recruitment.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/11/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("vacancyService")
public class VacancyServiceImpl implements VacancyService{
    @Autowired
    private VacancyDao vacancyDao;


    @Override
    public void create(Vacancy vacancy) {
        vacancyDao.create(vacancy);
    }

    @Override
    public void update(Vacancy vacancy){
        vacancyDao.update(vacancy);
    }

    @Override
    public void removeVacancy(Vacancy vacancy) {
        vacancyDao.removeVacancy(vacancy);
    }

    @Override
    public Vacancy findVacancyById(String id) {
        return vacancyDao.findVacancyById(id);
    }

    @Override
    public List<Vacancy> findAllVacancy(){
        return vacancyDao.findAllVacancy();
    }


}
