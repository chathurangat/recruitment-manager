package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class CheckBoxDictionary extends ApplicationFieldDictionary{

    //    @Id
//    private String id;

    @NotBlank
    private String groupName;

    private List<CheckBoxValue> checkBoxValueList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

    public List<CheckBoxValue> getCheckBoxValueList() {
        return checkBoxValueList;
    }

    public void setCheckBoxValueList(List<CheckBoxValue> checkBoxValueList) {
        this.checkBoxValueList = checkBoxValueList;
    }
}
