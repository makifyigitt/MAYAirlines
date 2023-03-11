package com.may.MAYAirlines.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotNull
    @Size(min = 4,max = 30)
    private String username;
    @NotNull
    @Size(min = 4,max = 30)
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String surname;
    @NotNull
    @Email
    private String email;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String firstName, String surname, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
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
}
