package com.may.MAYAirlines.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "airports")
public class Airport {

    public Airport() {
        this.arrivalAirportList=new ArrayList<>();
        this.deparetureAirportList=new ArrayList<>();
    }

    public Airport(String name, String airportCode, City city) {
        this.name = name;
        this.airportCode=airportCode;
        this.city = city;
        this.arrivalAirportList=new ArrayList<>();
        this.deparetureAirportList=new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(max = 3)
    @Column(name = "airport_code",unique = true)
    private String airportCode;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "create_date")
    private Date createDate = new Date();
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "status")
    private boolean status = true;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> deparetureAirportList = new ArrayList<>();
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalAirportList = new ArrayList<>();

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    public List<Flight> getDeparetureAirportList() {
        return deparetureAirportList;
    }

    public void setDeparetureAirportList(List<Flight> deparetureAirportList) {
        this.deparetureAirportList = deparetureAirportList;
    }

    public List<Flight> getArrivalAirportList() {
        return arrivalAirportList;
    }

    public void setArrivalAirportList(List<Flight> arrivalAirportList) {
        this.arrivalAirportList = arrivalAirportList;
    }
}
