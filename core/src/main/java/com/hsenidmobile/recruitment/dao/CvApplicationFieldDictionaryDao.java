package com.hsenidmobile.recruitment.dao;

import com.hsenidmobile.recruitment.model.ApplicationFieldDictionary;

import java.util.List;

public interface CvApplicationFieldDictionaryDao {

    /**
     * <p>
     *     create cv application section field dictionary
     * </p>
     * @param applicationFieldDictionary as {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary}
     */
    void createCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary);

    /**
     * <p>
     *     update cv application section field dictionary
     * </p>
     * @param applicationFieldDictionary {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary}
     */
    void updateCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary);

    /**
     * <p>
     *     remove the given cv application section field dictionary
     * </p>
     * @param applicationFieldDictionary as {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary}
     */
    void removeCvSectionFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary);

    /**
     * <p>
     *     find cv application section field dictionary with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link com.hsenidmobile.recruitment.model.ApplicationFieldDictionary}
     */
    ApplicationFieldDictionary findCvSectionFieldDictionaryById(String id);

    /**
     * <p>
     *     find all cv application section field dictionary
     * </p>
     * @return instance of {@link List<ApplicationFieldDictionary>}
     */
    List<ApplicationFieldDictionary> findAllCvSectionFieldDictionary();
}
