package com.hsenidmobile.recruitment.service.impl;

import com.hsenidmobile.recruitment.dao.DropdownOptionsDao;
import com.hsenidmobile.recruitment.model.DropDownOption;
import com.hsenidmobile.recruitment.service.DropdownOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dropdownOptionsService")
public class DropdownOptionsServiceImpl implements DropdownOptionsService {

    @Autowired
    private DropdownOptionsDao dropdownOptionsDao;

    @Override
    public void createDropDownOption(DropDownOption dropDownOption) {
        dropdownOptionsDao.createDropDownOption(dropDownOption);
    }

    @Override
    public void insertDropDownOption(DropDownOption dropDownOption){
        dropdownOptionsDao.insertDropDownOption(dropDownOption);
    }
    @Override
    public void updateDropDownOption(DropDownOption dropDownOption) {
        dropdownOptionsDao.updateDropDownOption(dropDownOption);
    }

    @Override
    public void removeDropDownOption(DropDownOption dropDownOption) {
        dropdownOptionsDao.removeDropDownOption(dropDownOption);
    }

    @Override
    public DropDownOption findDropDownOptionById(String id) {
        return dropdownOptionsDao.findDropDownOptionById(id);
    }

    @Override
    public List<DropDownOption> findAllDropDownOption(){
        return dropdownOptionsDao.findAllDropDownOption();
    }
}