package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.CvApplicationField;
import java.util.List;

public interface CvApplicationFieldService  {

    /**
     * <p>
     *     create cv application section field
     * </p>
     * @param cvApplicationField as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    void createCvSectionField(CvApplicationField cvApplicationField);

    /**
     * <p>
     *     update cv application section field
     * </p>
     * @param cvApplicationField as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    void updateCvSectionField(CvApplicationField cvApplicationField);

    /**
     * <p>
     *     remove the given cv application section field
     * </p>
     * @param cvApplicationField as {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    void removeCvSectionField(CvApplicationField cvApplicationField);


    /**
     * <p>
     *     find cv application section field with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link com.hsenidmobile.recruitment.model.CvApplicationField}
     */
    CvApplicationField findCvSectionFieldById(String id);

    /**
     * <p>
     *     find all cv application section field
     * </p>
     * @return instance of {@link List<CvApplicationField>}
     */
    List<CvApplicationField> findAllCvSectionField();
}
