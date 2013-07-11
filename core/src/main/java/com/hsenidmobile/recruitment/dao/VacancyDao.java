package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.Vacancy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/11/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VacancyDao {


    void create(Vacancy vacancy);
    /**
     * <p>
     *     create vacancy
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.CvApplicationSection}
     */

    void update(Vacancy vacancy);
    /**
     * <p>
     *     update vacancy
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.CvApplicationSection}
     */




    Vacancy findVacancyById(String id);
    /**
     * <p>
     *     find vacancy with given id
     * </p>
     * @return instance of {@link Vacancy}
     */


    List<Vacancy> findAllVacancy();


    void removeVacancy(Vacancy vacancy);
}
