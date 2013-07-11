package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.Vacancy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/11/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VacancyService {
    /**
     * <p>
     *     update vacancy
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    void create(Vacancy vacancy);
    /**
     * <p>
     *     create vacancy
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    void update(Vacancy vacancy);

    void removeVacancy(Vacancy vacancy);

    Vacancy findVacancyById(String id);

    List<Vacancy> findAllVacancy();

}
