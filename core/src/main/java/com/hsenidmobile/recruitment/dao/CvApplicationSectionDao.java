package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.CvApplicationSection;

public interface CvApplicationSectionDao {

    /**
     * <p>
     *     create cv application section
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
    void removeCvSection(CvApplicationSection cvApplicationSection);


    /**
     * <p>
     *     find cv application section with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link CvApplicationSection}
     */
    CvApplicationSection findCvSectionById(String id);
}
