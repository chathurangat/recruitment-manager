package com.hsenidmobile.recruitment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;

@Document
public class Vacancy implements Serializable {

    @Id
    private String id;
    private String cvApplicationTemplateId;
    private String filename;
    private String filePath;
    private CommonsMultipartFile fileData;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

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

}