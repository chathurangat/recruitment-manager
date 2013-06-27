package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cv_template")
public class CvApplicationTemplate implements Serializable {

    @Id
    private String id;
    @NotBlank
    private String cvHeaderEn;
    @NotBlank
    private String cvHeaderSi;
    @NotBlank
    private String cvHeaderTa;

    private List<CvApplicationSection> cvApplicationSectionList = new ArrayList<CvApplicationSection>();
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCvHeaderEn() {
        return cvHeaderEn;
    }

    public void setCvHeaderEn(String cvHeaderEn) {
        this.cvHeaderEn = cvHeaderEn;
    }

    public String getCvHeaderSi() {
        return cvHeaderSi;
    }

    public void setCvHeaderSi(String cvHeaderSi) {
        this.cvHeaderSi = cvHeaderSi;
    }

    public String getCvHeaderTa() {
        return cvHeaderTa;
    }

    public void setCvHeaderTa(String cvHeaderTa) {
        this.cvHeaderTa = cvHeaderTa;
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
