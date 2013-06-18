package com.hsenidmobile.recruitment.web.dao;

import com.hsenidmobile.recruitment.web.model.CvSectionInsert;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 6/17/13
 * Time: 5:11 PM
 * To change this template use File | Settings | File Templates.
 */


@Repository("cvSectionInsertDao")
public interface CvSectionInsertDao {






        /**
         * <p>
         *     create new CvSection
         * </p>
         * @param cvSectionInsert as {@link CvSectionInsert}
         */
        void addCvSection(CvSectionInsert cvSectionInsert);

        /**
         * <p>
         *     remove the given applicant
         * </p>
         * @param cvSectionInsert as {@link CvSectionInsert}
         */
        void removeCvSection(CvSectionInsert cvSectionInsert);


        /**
         * <p>
         *     find applicant with given id
         * </p>
         * @param id as {@link String}
         * @return instance of {@link CvSectionInsert}
         */
        CvSectionInsert findCvSectionById(String id);



}

