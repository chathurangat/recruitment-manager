package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
public class Applicant extends User implements Serializable{

    @Id
    private String id;
    private String applicantName;
    private List<CvApplication> cvApplicationList = new ArrayList<CvApplication>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public List<CvApplication> getCvApplicationList() {
        return cvApplicationList;
    }

    public void setCvApplicationList(List<CvApplication> cvApplicationList) {
        this.cvApplicationList = cvApplicationList;
    }

    public void addCvApplication(CvApplication cvApplication){
        cvApplicationList.add(cvApplication);
    }

    public void submitApplication(CvApplication cvApplication){
        cvApplication.setUserApplied(this);
    }
}
