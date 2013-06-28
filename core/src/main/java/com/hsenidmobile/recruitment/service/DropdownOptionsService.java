package com.hsenidmobile.recruitment.service;

import com.hsenidmobile.recruitment.model.DropDownOption;

import java.util.List;

public interface DropdownOptionsService {

    /**
     * <p>
     *     create option for Dropdown field
     * </p>
     * @param dropDownOption as {@link com.hsenidmobile.recruitment.model.DropDownOption}
     */
    void createDropDownOption(DropDownOption dropDownOption);

    /**
     * <p>
     *     insert option for Dropdown field
     * </p>
     * @param dropDownOption as {@link com.hsenidmobile.recruitment.model.DropDownOption}
     */
    void insertDropDownOption(DropDownOption dropDownOption);

    /**
     * <p>
     *     update option for Dropdown field
     * </p>
     * @param dropDownOption as {@link com.hsenidmobile.recruitment.model.DropDownOption}
     */
    void updateDropDownOption(DropDownOption dropDownOption);

    /**
     * <p>
     *     remove the given option from Dropdown field
     * </p>
     * @param dropDownOption as {@link com.hsenidmobile.recruitment.model.DropDownOption}
     */
    void removeDropDownOption(DropDownOption dropDownOption);


    /**
     * <p>
     *     find dropdown option with given id
     * </p>
     * @param id as {@link String}
     * @return instance of {@link com.hsenidmobile.recruitment.model.DropDownOption}
     */
    DropDownOption findDropDownOptionById(String id);

    /**
     * <p>
     *     find all dropdown option
     * </p>
     * @return instance of {@link java.util.List <DropDownOption>}
     */
    List<DropDownOption> findAllDropDownOption();
}
