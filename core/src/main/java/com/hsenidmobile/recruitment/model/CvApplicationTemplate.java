package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Document
public class CvApplicationTemplate implements Serializable {

    @Id
    private String id;
    private String cvHeader;
    private List<CvApplicationSection> cvApplicationSectionList;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
