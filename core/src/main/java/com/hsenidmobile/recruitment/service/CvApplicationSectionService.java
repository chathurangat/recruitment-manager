package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.CvApplicationSection;

/**
 * Created with IntelliJ IDEA.
 * User: nilaxan
 * Date: 6/17/13
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CvApplicationSectionService {

    /**
     * <p>
     *     create application section
     * </p>
     * @param cvApplicationSection as {@link com.hsenidmobile.recruitment.model.CvApplicationSection}
     */
    void create(CvApplicationSection cvApplicationSection);

    /**
     * <p>
     *     update cv application section
     * </p>
     * @param cvApplicationSection as {@link CvApplicationSection}
     */
    void update(CvApplicationSection cvApplicationSection);

    /**
     * <p>
     *     remove the given cv application section
     * </p>
     * @param cvApplicationSection as {@link CvApplicationSection}
     */
    void removeCvTemplate(CvApplicationSection cvApplicationSection);


    /**
     * <p>
     *     find cv template with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link CvApplicationSection}
     */
    CvApplicationSection findCvSectionById(String id);
}
