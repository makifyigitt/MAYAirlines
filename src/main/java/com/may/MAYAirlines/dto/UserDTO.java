package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.User;

import java.util.Date;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String email;
   private String phoneNumber;
    private String address;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public UserDTO(User user){
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.firstName=user.getFirstName();
        this.surname=user.getSurname();
        this.email=user.getEmail();
        this.phoneNumber=user.getPhoneNumber();
        this.address=user.getAddress();
        this.createDate=user.getCreateDate();
        this.updateDate=user.getUpdateDate();
        this.status=user.isStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
