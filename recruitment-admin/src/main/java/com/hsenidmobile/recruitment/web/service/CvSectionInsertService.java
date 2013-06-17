package com.hsenidmobile.recruitment.web.service;

import com.hsenidmobile.recruitment.web.model.CvSectionInsert;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 6/17/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CvSectionInsertService {
    /**
     * <p>
     *     create new applicant
     * </p>
     * @param cvSectionInsert as {@link CvSectionInsert}
     */
    void addCvSection(CvSectionInsert cvSectionInsert);

    /**
     * <p>
     *     remove the given applicant
     * </p>
     * @param cvSectionInsert as {@link CvSectionInsert}
     */
    void removeCvSection(CvSectionInsert cvSectionInsert);


    /**
     * <p>
     *     find applicant with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link CvSectionInsert}
     */
    CvSectionInsert findCvsectioById(String id);



}
