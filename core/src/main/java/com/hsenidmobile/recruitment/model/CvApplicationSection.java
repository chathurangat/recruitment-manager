package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *     represents the section of an application template.
 *     a given application template can be have several application section fields.
 *     eg:- personal details, educational details, work experiences
 * </p>
 */
@Document
public class CvApplicationSection implements Serializable{

    @Id
    private String id;
    private String sectionName;
    private  CvApplicationTemplate cvApplicationTemplate;
    private boolean status;
    private Integer priority;
    private List<CvApplicationField> cvApplicationFieldList;


    public CvApplicationTemplate getCvApplicationTemplate() {
        return cvApplicationTemplate;
    }

    public void setCvApplicationTemplate(CvApplicationTemplate cvApplicationTemplate) {
        this.cvApplicationTemplate = cvApplicationTemplate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<CvApplicationField> getCvApplicationFieldList() {
        return cvApplicationFieldList;
    }

    public void setCvApplicationFieldList(List<CvApplicationField> cvApplicationFieldList) {
        this.cvApplicationFieldList = cvApplicationFieldList;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
