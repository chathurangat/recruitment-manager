package com.hsenidmobile.recruitment.web.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 6/17/13
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Document
public class CvSectionInsert {
    @Id
    private String id;
    private String sectionName_EN;
    private String sectionName_SI;
    private String sectionName_TA;
    private String status;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSectionName_EN(){
        return sectionName_EN;
    }
    public void setSectionName_EN(String sectionName_EN){
        this.sectionName_EN=sectionName_EN;
    }
    public String getSectionName_SI(){
        return sectionName_SI;
    }
    public void setSectionName_SI(String sectionName_SI){
        this.sectionName_SI=sectionName_SI;
    }
    public String getSectionName_TA(){
        return sectionName_TA;
    }
    public void setSectionName_TA(String sectionName_TA){
        this.sectionName_TA=sectionName_TA;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }


}
