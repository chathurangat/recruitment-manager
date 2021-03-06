package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *     represents the section of an application template.
 *     a given application template can be have several application section fields.
 *     eg:- personal details, educational details, work experiences
 * </p>
 */
@Document(collection = "master_cv_section")
public class CvApplicationSection implements Serializable{

    @Id
    private String id;
    @NotBlank
    private String sectionNameEn;
    @NotBlank
    private String sectionNameSi;
    @NotBlank
    private String sectionNameTa;
    private boolean status;
    private Integer priority;
    private List<CvApplicationField> cvApplicationFieldList;

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

    public String getSectionNameEn() {
        return sectionNameEn;
    }

    public void setSectionNameEn(String sectionNameEn) {
        this.sectionNameEn = sectionNameEn;
    }

    public String getSectionNameSi() {
        return sectionNameSi;
    }

    public void setSectionNameSi(String sectionNameSi) {
        this.sectionNameSi = sectionNameSi;
    }

    public String getSectionNameTa() {
        return sectionNameTa;
    }

    public void setSectionNameTa(String sectionNameTa) {
        this.sectionNameTa = sectionNameTa;
    }
}
