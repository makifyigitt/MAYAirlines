package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Airport;

import java.util.Date;


public class AirportDTO {
    private int id;
    private String name;
    private String airportCode;
    private String cityName;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public AirportDTO(Airport airport){
        this.id=airport.getId();
        this.name=airport.getName();
        this.airportCode=airport.getAirportCode();
        this.cityName=airport.getCity().getName();
        this.createDate=airport.getCreateDate();
        this.updateDate=airport.getUpdateDate();
        this.status=airport.isStatus();
    }

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

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
