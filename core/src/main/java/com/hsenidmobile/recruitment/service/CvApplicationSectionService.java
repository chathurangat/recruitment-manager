package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.CvApplicationSection;
import java.util.List;

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
    void removeCvSection(CvApplicationSection cvApplicationSection);


    /**
     * <p>
     *     find cv section with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link CvApplicationSection}
     */
    CvApplicationSection findCvSectionById(String id);

    /**
     * <p>
     *     find all cv section with given id
     * </p>
     *
     */
    List<CvApplicationSection> findAllCvSection();
}
