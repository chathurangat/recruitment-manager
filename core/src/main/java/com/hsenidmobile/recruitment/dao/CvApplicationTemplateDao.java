package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.CvApplicationTemplate;

public interface CvApplicationTemplateDao {

    /**
     * <p>
     *     create application template
     * </p>
     * @param cvApplicationTemplate as {@link CvApplicationTemplate}
     */
    void create(CvApplicationTemplate cvApplicationTemplate);

    /**
     * <p>
     *     update cv template
     * </p>
     * @param cvApplicationTemplate as {@link CvApplicationTemplate}
     */
    void update(CvApplicationTemplate cvApplicationTemplate);

    /**
     * <p>
     *     remove the given cv template
     * </p>
     * @param cvApplicationTemplate as {@link CvApplicationTemplate}
     */
    void removeCvTemplate(CvApplicationTemplate cvApplicationTemplate);


    /**
     * <p>
     *     find cv template with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link CvApplicationTemplate}
     */
    CvApplicationTemplate findCvTemplateById(String id);


}
