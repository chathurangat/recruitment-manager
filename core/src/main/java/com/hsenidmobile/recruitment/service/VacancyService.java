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
     *     create vacancy template
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.Vacancy}
     */
    void create(Vacancy vacancy);

     /**
     * <p>
     *     update vacancy
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.Vacancy}
     */
    void update(Vacancy vacancy);

    /**
     * <p>
     *     remove the vacancy
     * </p>
     * @param vacancy as {@link com.hsenidmobile.recruitment.model.Vacancy}
     */
    void removeVacancy(Vacancy vacancy);

    /**
     * <p>
     *     find vacancy with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link com.hsenidmobile.recruitment.model.Vacancy}
     */
    Vacancy findVacancyById(String id);

    /**
     * <p>
     *     find all available vacancy
     * </p>
     * @return instance of {@link List< com.hsenidmobile.recruitment.model.Vacancy>}
     */
    List<Vacancy> findAllVacancy();

}
