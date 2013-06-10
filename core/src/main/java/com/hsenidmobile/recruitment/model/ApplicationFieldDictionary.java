package com.hsenidmobile.recruitment.model;

import java.io.Serializable;

/**
 * <p>
 *     represents an actual field available in the application template.
 *    e.g:- first name field, dob field, address field, experience field etc...
 *    each application field will be assigned under a section of the CV template.{@link CvApplicationSection}
 * </p>
 */
public class ApplicationFieldDictionary implements Serializable {

    protected Long id;
    protected String htmlComponent;
    protected String description;
    protected boolean status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
