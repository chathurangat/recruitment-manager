package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     represents an actual field available in the application template.
 *    e.g:- first name field, dob field, address field, experience field etc...
 *    each application field will be assigned under a section of the CV template.{@link CvApplicationSection}
 * </p>
 */
@Document(collection = "cv_field_dictionary")
public  class ApplicationFieldDictionary implements Serializable {

    @Id
    protected String id;
    @NotBlank
    protected String labelEn;
    @NotBlank
    protected String labelSi;
    @NotBlank
    protected String labelTa;
    protected List<ApplicationFieldDictionaryValidation> applicationFieldDictionaryValidationList = new ArrayList<ApplicationFieldDictionaryValidation>();
    protected String htmlComponent;
    protected String description;
    protected boolean status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlComponent() {
        return htmlComponent;
    }

    public void setHtmlComponent(String htmlComponent) {
        this.htmlComponent = htmlComponent;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLabelEn() {
        return labelEn;
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }
    public String getLabelSi() {
        return labelSi;
    }

    public void setLabelSi(String labelSi) {
        this.labelSi = labelSi;
    }
    public String getLabelTa() {
        return labelTa;
    }

    public void setLabelTa(String labelTa) {
        this.labelTa = labelTa;
    }

    public List<ApplicationFieldDictionaryValidation> getApplicationFieldDictionaryValidationList() {
        return applicationFieldDictionaryValidationList;
    }

    public void setApplicationFieldDictionaryValidationList(List<ApplicationFieldDictionaryValidation> applicationFieldDictionaryValidationList) {
        this.applicationFieldDictionaryValidationList = applicationFieldDictionaryValidationList;
    }
}
