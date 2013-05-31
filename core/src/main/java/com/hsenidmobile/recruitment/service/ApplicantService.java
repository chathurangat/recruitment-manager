package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.Applicant;

public interface ApplicantService {

    /**
     * <p>
     *     create new applicant
     * </p>
     * @param applicant as {@link Applicant}
     */
    void addApplicant(Applicant applicant);

}
