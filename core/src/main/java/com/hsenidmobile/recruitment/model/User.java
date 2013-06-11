package com.hsenidmobile.recruitment.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    protected Long userId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Date registeredDate;
    protected boolean status;
    protected String openIdProvider;
    protected String username;
    protected String name;
    protected String gender;
    protected String homeTown;
    protected String location;
    protected String socialNetworkResourceId;
    protected String defaultMd5HashedPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getOpenIdProvider() {
        return openIdProvider;
    }

    public void setOpenIdProvider(String openIdProvider) {
        this.openIdProvider = openIdProvider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSocialNetworkResourceId() {
        return socialNetworkResourceId;
    }

    public void setSocialNetworkResourceId(String socialNetworkResourceId) {
        this.socialNetworkResourceId = socialNetworkResourceId;
    }

    public String getDefaultMd5HashedPassword() {
        return defaultMd5HashedPassword;
    }

    public void setDefaultMd5HashedPassword(String defaultMd5HashedPassword) {
        this.defaultMd5HashedPassword = defaultMd5HashedPassword;
    }
}
