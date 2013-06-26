package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.Applicant;
import com.hsenidmobile.recruitment.model.CvApplication;

import java.util.List;

public interface CvApplicationDao {

    /**
     * <p>
     *     create new cv application instance.
     *     this method will be called when it needs to create new CV application instance.
     * </p>
     * @param cvApplication as {@link CvApplication}
     * @return cvApplication ID as {@link String}
     */
    String create(CvApplication cvApplication);

    /**
     * <p>
     *     remove the given cv application instance
     * </p>
     * @param cvApplication as {@link CvApplication}
     */
    void remove(CvApplication cvApplication);

    /**
     * <p>
     *     find the relevant {@link CvApplication} that matches the given application Id
     * </p>
     * @param applicationId as {@link String}
     * @return found instance of {@link CvApplication}
     */
    CvApplication findById(String applicationId);

    /**
     * <P>
     *     find a list of cv applications applied by applicant
     * </P>
     * @return  list of {@link List<CvApplication>} applied by the given applicant
     */
    List<CvApplication> findCvApplicationsByApplicant(Applicant applicant);
}
