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
     * @param applicant as {@link Applicant}
     */
    void removeApplicant(Applicant applicant);


    /**
     * <p>
     *     find applicant with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link Applicant}
     */
    Applicant findApplicantById(String id);


    /**
     * <p>
     *     find he relevant applicant instance based on the social network details provided.
     *     this method will be mainly used for retrieving the user credentials to check the
     *     user authentication
     * </p>
     * @param socialNetwork  as {@link String} - represents the social network (e.g:- facebook or google)
     * @param username as {@link String} - registered username in social network
     * @param socialNetworkUserId as {@link String} - registered id in social network
     * @param status as {@link Boolean} - active or inactive status of the applicant
     * @return an instance of {@link Applicant}
     */
    Applicant findApplicantFromSocialNetworkDetails(String socialNetwork,String username,String socialNetworkUserId,boolean status);


    /**
     * <p>
     *     find he relevant applicant instance based on the social network details provided.
     *     this method will be mainly used for retrieving the user credentials to check the
     *     user authentication
     * </p>
     * @param socialNetwork  as {@link String} - represents the social network (e.g:- facebook or google)
     * @param username as {@link String} - registered username in social network
     * @param status as {@link Boolean} - active or inactive status of the applicant
     * @return an instance of {@link Applicant}
     */
    Applicant findApplicantFromSocialNetworkDetails(String socialNetwork,String username,boolean status);
}

