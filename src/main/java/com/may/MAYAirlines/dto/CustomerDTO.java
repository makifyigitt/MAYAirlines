package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Customer;


import java.util.List;
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
}
