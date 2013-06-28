package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.util.List;

public class DropDownDictionary extends ApplicationFieldDictionary{
    @Id
    private String id;
    @NotBlank
    private String nameEn;
    @NotBlank
    private String nameSi;
    @NotBlank
    private String nameTa;

    private List<DropDownOption> dropDownOptionList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameSi() {
        return nameSi;
    }

    public void setNameSi(String nameSi) {
        this.nameSi = nameSi;
    }

    public String getNameTa() {
        return nameTa;
    }

    public void setNameTa(String nameTa) {
        this.nameTa = nameTa;
    }

    public List<DropDownOption> getDropDownOptionList() {
        return dropDownOptionList;
    }

    public void setDropDownOptionList(List<DropDownOption> dropDownOptionList) {
        this.dropDownOptionList = dropDownOptionList;
    }
}
