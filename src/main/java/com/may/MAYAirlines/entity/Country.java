package com.may.MAYAirlines.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    public Country() {
        this.cities=new ArrayList<>();
    }

    public Country(int id,
                   String name,
                   String countryCode) {
        this.id=id;
        this.name = name;
        this.countryCode = countryCode;
        this.cities=new ArrayList<>();
    }

    public Country(String name,
                   String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
        this.cities=new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "country_code",unique = true)
    private String countryCode;

    @OneToMany(mappedBy = "country")
    private List<City> cities = new ArrayList<>();

    @Column(name = "create_date")
    private Date createDate = new Date();
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "status")
    private boolean status = true;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
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
