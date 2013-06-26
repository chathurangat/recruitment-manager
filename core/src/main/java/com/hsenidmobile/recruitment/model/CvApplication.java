package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;

@Document(collection = "cv_application")
public class CvApplication implements Serializable{

    @Id
    private String id;
    private String applicationName;
    private User userApplied;
//    private Vacancy vacancy;
    private CvApplicationTemplate cvApplicationTemplate;
    private Date dateApplied;
    private ApplicationStatus applicationStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public User getUserApplied() {
        return userApplied;
    }

    public void setUserApplied(User userApplied) {
        this.userApplied = userApplied;
    }

    public CvApplicationTemplate getCvApplicationTemplate() {
        return cvApplicationTemplate;
    }

    public void setCvApplicationTemplate(CvApplicationTemplate cvApplicationTemplate) {
        this.cvApplicationTemplate = cvApplicationTemplate;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
