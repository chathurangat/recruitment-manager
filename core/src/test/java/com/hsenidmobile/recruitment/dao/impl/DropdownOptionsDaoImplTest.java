package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.CommonDaoTest;
import com.hsenidmobile.recruitment.dao.DropdownOptionsDao;
import com.hsenidmobile.recruitment.model.DropDownOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownOptionsDaoImplTest extends CommonDaoTest {

    @Autowired
    private DropdownOptionsDao dropdownOptionsDao;

    @Test
    public void CreateDropDownOption() {
       //create dropdown option
        DropDownOption dropDownOption = new DropDownOption();

        dropDownOption.setNameSi("Juice");
        dropDownOption.setNameSi("Juice si");
        dropDownOption.setNameTa("Juice ta");

        //now we will create new dropdown option with given data
        dropdownOptionsDao.createDropDownOption(dropDownOption);
        Assert.assertNotNull(dropDownOption.getId());

        DropDownOption dropDownOptionFound  = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNotNull(dropDownOptionFound);

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
   /*     dropdownOptionsDao.removeDropDownOption(dropDownOption);
        DropDownOption dropDownOptionFound1 = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNull(dropDownOptionFound1);
        */
    }

    public void InsertDropDownOption() {
     //todo
    }

    public void UpdateDropDownOption() {
      //todo
    }
}


