package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cv_field_validations_criteria")
public class ApplicationFieldDictionaryValidation {

    @Id
    private String id;
    private String validationCriteria;

    public String getValidationCriteria() {
        return validationCriteria;
    }

    public void setValidationCriteria(String validationCriteria) {
        this.validationCriteria = validationCriteria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
