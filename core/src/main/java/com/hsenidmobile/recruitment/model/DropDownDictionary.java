package com.hsenidmobile.recruitment.model;

import java.util.List;

public class DropDownDictionary extends ApplicationFieldDictionary{

    private String nameEn;
    private String nameSi;
    private String nameTa;
    private List<DropDownOption> dropDownOptionList;

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
