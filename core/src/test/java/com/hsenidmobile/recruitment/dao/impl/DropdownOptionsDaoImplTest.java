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

        dropDownOption.setNameEn("Male");
        dropDownOption.setNameSi("Male si");
        dropDownOption.setNameTa("Male ta");

        //now we will create new dropdown option with given data
        dropdownOptionsDao.createDropDownOption(dropDownOption);
        
        //make sure the values are correct when create a dropdwon option using 'createDropDownOption' method
        Assert.assertNotNull(dropDownOption);
        Assert.assertNotNull(dropDownOption.getId());
        Assert.assertEquals(dropDownOption.getNameEn(),"Male");
        Assert.assertEquals(dropDownOption.getNameSi(),"Male si");
        Assert.assertEquals(dropDownOption.getNameTa(),"Male ta");

        //make sure the values are correct when retrieving  a dropdwon option using 'findDropDownOptionById' method
        DropDownOption dropDownOptionFound  = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNotNull(dropDownOptionFound);
        Assert.assertNotNull(dropDownOptionFound.getId());
        Assert.assertEquals(dropDownOptionFound.getNameEn(),"Male");
        Assert.assertEquals(dropDownOptionFound.getNameSi(),"Male si");
        Assert.assertEquals(dropDownOptionFound.getNameTa(),"Male ta");

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        //make sure the values are exists when removing a dropdwon option using 'removeDropDownOption' method
        dropdownOptionsDao.removeDropDownOption(dropDownOption);
        DropDownOption dropDownOptionFound1 = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNull(dropDownOptionFound1);
    }

    @Test
    public void InsertDropDownOption() {

        //insert dropdown option
        DropDownOption dropDownOption = new DropDownOption();

        dropDownOption.setNameEn("Female");
        dropDownOption.setNameSi("Female si");
        dropDownOption.setNameTa("Female ta");

        //now we will insert new dropdown option with given data
        dropdownOptionsDao.insertDropDownOption(dropDownOption);

        //make sure the values are correct when insert a dropdwon option using 'insertDropDownOption' method
        Assert.assertNotNull(dropDownOption);
        Assert.assertNotNull(dropDownOption.getId());
        Assert.assertEquals(dropDownOption.getNameEn(),"Female");
        Assert.assertEquals(dropDownOption.getNameSi(),"Female si");
        Assert.assertEquals(dropDownOption.getNameTa(),"Female ta");

        //make sure the values are correct when retrieving  a dropdwon option using 'findDropDownOptionById' method
        DropDownOption dropDownOptionFound  = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNotNull(dropDownOptionFound);
        Assert.assertNotNull(dropDownOptionFound.getId());
        Assert.assertEquals(dropDownOptionFound.getNameEn(),"Female");
        Assert.assertEquals(dropDownOptionFound.getNameSi(),"Female si");
        Assert.assertEquals(dropDownOptionFound.getNameTa(),"Female ta");

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        //make sure the values are exists when removing a dropdwon option using 'removeDropDownOption' method
        dropdownOptionsDao.removeDropDownOption(dropDownOption);
        DropDownOption dropDownOptionFound1 = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNull(dropDownOptionFound1);
    }

    @Test
    public void UpdateDropDownOption() {

        //insert dropdown option
        DropDownOption dropDownOption = new DropDownOption();

        dropDownOption.setNameEn("Male");
        dropDownOption.setNameSi("Male si");
        dropDownOption.setNameTa("Male ta");

        //now we will update a dropdown option with given data
        dropdownOptionsDao.updateDropDownOption(dropDownOption);

        //make sure the values are correct when insert a dropdwon option using 'updateDropDownOption' method
        Assert.assertNotNull(dropDownOption);
        Assert.assertNotNull(dropDownOption.getId());
        Assert.assertEquals(dropDownOption.getNameEn(),"Male");
        Assert.assertEquals(dropDownOption.getNameSi(),"Male si");
        Assert.assertEquals(dropDownOption.getNameTa(),"Male ta");

        //make sure the values are correct when retrieving  a dropdwon option using 'findDropDownOptionById' method
        DropDownOption dropDownOptionFound  = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNotNull(dropDownOptionFound);
        Assert.assertNotNull(dropDownOptionFound.getId());
        Assert.assertEquals(dropDownOptionFound.getNameEn(),"Male");
        Assert.assertEquals(dropDownOptionFound.getNameSi(),"Male si");
        Assert.assertEquals(dropDownOptionFound.getNameTa(),"Male ta");

        //make sure to remove the test data once the test execution is completed.if you need to retain the test data just comment below lines
        //make sure the values are exists when removing a dropdwon option using 'removeDropDownOption' method
        dropdownOptionsDao.removeDropDownOption(dropDownOption);
        DropDownOption dropDownOptionFound1 = dropdownOptionsDao.findDropDownOptionById(dropDownOption.getId());
        Assert.assertNull(dropDownOptionFound1);
    }
}


