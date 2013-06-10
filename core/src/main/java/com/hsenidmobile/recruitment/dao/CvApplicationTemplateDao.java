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

}
