package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Airport;
import com.may.MAYAirlines.entity.City;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CityDTO {
    private int cityId;
    private String name;
    private String countryName;
    private List<AirportDTO> airportList;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public CityDTO(City city){
        this.cityId= city.getId();
        this.name= city.getName();
        this.countryName=city.getCountry().getName();
        this.airportList=city.getAirportList()
                .stream()
                .map(AirportDTO::new)
                .collect(Collectors.toList());
        this.createDate=city.getCreateDate();
        this.updateDate=city.getUpdateDate();
        this.status= city.isStatus();
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<AirportDTO> getAirportList() {
        return airportList;
    }

    public void setAirportList(List<AirportDTO> airportList) {
        this.airportList = airportList;
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
