package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Customer;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomerDTO {
    private int id;
    private String username;
    private String firstName;
    private String surname;
    private Boolean status;
    private String email;
    private String phoneNumber;
    private String address;
    private List<ReservationDTO> reservationList;
    private Date createDate;
    private Date updateDate;

    public CustomerDTO(Customer customer){
        this.id= customer.getId();
        this.username=customer.getUsername();
        this.firstName= customer.getFirstName();
        this.surname= customer.getSurname();
        this.status = customer.isStatus();
        this.email= customer.getEmail();
        this.phoneNumber= customer.getPhoneNumber();
        this.address= customer.getAddress();
        this.reservationList=customer.getReservationList()
                .stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
        this.createDate=customer.getCreateDate();
        this.updateDate=customer.getUpdateDate();
    }

    //Included the equals and hashcode methods to disable the reservation list when calculate hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(surname, that.surname) && Objects.equals(status, that.status) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(createDate, that.createDate) && Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, surname, status, email, phoneNumber, address, createDate, updateDate);
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public List<ReservationDTO> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationDTO> reservationList) {
        this.reservationList = reservationList;
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
}
