package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cv_template")
public class CvApplicationTemplate implements Serializable {

    @Id
    private String id;
    private String cvHeader;
    private List<CvApplicationSection> cvApplicationSectionList = new ArrayList<CvApplicationSection>();
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvHeader() {
        return cvHeader;
    }

    public void setCvHeader(String cvHeader) {
        this.cvHeader = cvHeader;
    }

    public List<CvApplicationSection> getCvApplicationSectionList() {
        return cvApplicationSectionList;
    }

    public void setCvApplicationSectionList(List<CvApplicationSection> cvApplicationSectionList) {
        this.cvApplicationSectionList = cvApplicationSectionList;
    }

    public void add(CvApplicationSection cvApplicationSection){
        cvApplicationSectionList.add(cvApplicationSection);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
