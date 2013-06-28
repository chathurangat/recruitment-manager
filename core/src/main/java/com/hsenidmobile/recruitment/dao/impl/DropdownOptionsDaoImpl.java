package com.hsenidmobile.recruitment.dao.impl;

import com.hsenidmobile.recruitment.dao.DropdownOptionsDao;
import com.hsenidmobile.recruitment.model.DropDownOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dropdownOptionsDao")
public class DropdownOptionsDaoImpl implements DropdownOptionsDao {

    @Qualifier("mongoTemplate")
    @Autowired
    private MongoTemplate mongoTemplate;

    public static final String COLLECTION_NAME = "dropdown_options";


    /**
     * {@inheritDoc}
     */
    @Override
    public void createDropDownOption(DropDownOption dropDownOption) {
        if (!mongoTemplate.collectionExists(DropDownOption.class)) {
            mongoTemplate.createCollection(DropDownOption.class);
        }
        mongoTemplate.insert(dropDownOption, COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    public void insertDropDownOption(DropDownOption dropDownOption){
      mongoTemplate.insert(dropDownOption, COLLECTION_NAME);
     }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDropDownOption(DropDownOption dropDownOption){
        mongoTemplate.save(dropDownOption,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDropDownOption(DropDownOption dropDownOption) {
        mongoTemplate.remove(dropDownOption,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DropDownOption findDropDownOptionById(String id) {
        return mongoTemplate.findById(id,DropDownOption.class,COLLECTION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DropDownOption> findAllDropDownOption() {
        return mongoTemplate.findAll(DropDownOption.class);
    }
}
