package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.model.CvApplication;

import java.util.List;

public interface CvApplicationService {

    String create(CvApplication cvApplication);

    /**
     * <p>
     *     remove the given cv application section
     * </p>
     * @param cvApplication as {@link CvApplication}
     */
    void remove(CvApplication cvApplication);


    /**
     * <p>
     *     find cv section with given id
     * </p>
     * @param applicationId as {@link String}
     * @return instance of {@link CvApplication}
     */
    CvApplication findById(String applicationId);

    /**
     * <p>
     *     find all cv applications by applicant id
     * </p>
     *
     */
    List<CvApplication> findCvApplicationsByApplicant(Applicant applicant);

}
