package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vacancy {

    @Id
    private String id;
    private String cvApplicationTemplateId;
 //   private MultipartFile adImage;


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getCvApplicationTemplateId(){
        return cvApplicationTemplateId;
    }

    public void setCvApplicationTemplateId(String cvApplicationTemplateId){
        this.cvApplicationTemplateId = cvApplicationTemplateId;
    }

   /*
    public MultipartFile getAdImage() {
        return adImage;
    }


    public void setAdImage(MultipartFile adImage) {
        this.adImage = adImage;
    }

        */

}