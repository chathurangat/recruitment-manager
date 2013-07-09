package com.hsenidmobile.recruitment.model;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class RadioButtonDictionary extends ApplicationFieldDictionary{

    //    @Id
//    private String id;

    @NotBlank
    private String groupName;

    private List<RadioButtonValue> radioButtonValueList;

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

    public List<RadioButtonValue> getRadioButtonValueList() {
        return radioButtonValueList;
    }

    public void setRadioButtonValueList(List<RadioButtonValue> radioButtonValueList) {
        this.radioButtonValueList = radioButtonValueList;
    }
}