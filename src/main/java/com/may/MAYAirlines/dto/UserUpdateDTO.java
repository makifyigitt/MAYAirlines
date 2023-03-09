package com.may.MAYAirlines.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserUpdateDTO {
    @NotNull
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String surname;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",message ="{Your phone number format does not meet the phone number requirements.}")
    private String phoneNumber;
    @NotNull
    private String address;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

}
