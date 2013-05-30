package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.Applicant;

public interface ApplicantDao {

    /**
     * <p>
     *     create new applicant
     * </p>
     * @param applicant as {@link Applicant}
     */
    void addApplicant(Applicant applicant);

    /**
     * <p>
     *     remove the given applicant
     * </p>
     * @param applicant
     */
    void removeApplicant(Applicant applicant);


    /**
     * <p>
     *     find applicant with given id
     * </p>
     * @param id as {@link Long}
     * @return instance of {@link Applicant}
     */
    Applicant findApplicantById(Long id);
}
