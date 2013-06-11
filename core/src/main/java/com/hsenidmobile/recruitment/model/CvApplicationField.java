package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>
 *     represents an actual field available in the application template.
 *    e.g:- first name field, dob field, address field, experience field etc...
 *    each application field will be assigned under a section of the CV template.{@link CvApplicationSection}
 * </p>
 */
@Document
public class CvApplicationField implements Serializable{

    @Id
    private String id;
    private Integer priority;
    private ApplicationFieldDictionary applicationFieldDictionary;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ApplicationFieldDictionary getApplicationFieldDictionary() {
        return applicationFieldDictionary;
    }

    public void setApplicationFieldDictionary(ApplicationFieldDictionary applicationFieldDictionary) {
        this.applicationFieldDictionary = applicationFieldDictionary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
