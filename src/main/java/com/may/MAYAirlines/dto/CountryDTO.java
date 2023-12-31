package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Country;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CountryDTO {
    private int id;
    private String name;
    private String countryCode;
    private List<CityDTO> cities;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public CountryDTO(Country country){
        this.id=country.getId();
        this.name=country.getName();
        this.countryCode=country.getCountryCode();
        this.cities=country.getCities()
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
        this.createDate=country.getCreateDate();
        this.updateDate=country.getUpdateDate();
        this.status=country.isStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDTO that = (CountryDTO) o;
        return id == that.id && status == that.status && Objects.equals(name, that.name) && Objects.equals(countryCode, that.countryCode) && Objects.equals(cities, that.cities) && Objects.equals(createDate, that.createDate) && Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryCode, cities, createDate, updateDate, status);
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
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
