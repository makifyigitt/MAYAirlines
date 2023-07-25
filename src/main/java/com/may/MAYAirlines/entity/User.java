package com.may.MAYAirlines.entity;

import com.may.MAYAirlines.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 4,max = 30)
    @Column(name = "username")
    private String username;
    @NotNull
    @Size(min = 4,max = 30)
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",message ="{Your phone number format does not meet the phone number requirements.}")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Column(name = "create_date")
    private Date createDate = new Date(System.currentTimeMillis());

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "status")
    private boolean status = true;

    public User(){
    }

    public User(String username, String password, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
    }

    public User(String username, String password, String firstName, String surname,String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.email=email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
