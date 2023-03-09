package com.may.MAYAirlines.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "airplanes")
public class Airplane {

    public Airplane() {
        this.flightList=new ArrayList<>();
    }
    public Airplane(int id,String planeType, int capacity){
        this.id = id;
        this.planeType = planeType;
        this.capacity = capacity;
        this.flightList=new ArrayList<>();
    }

    public Airplane(String planeType, int capacity) {
        this.planeType = planeType;
        this.capacity = capacity;
        this.flightList=new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "plane_type")
    private String planeType;
    @NotNull
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "maintenance_date")
    private Date maintenanceDate;

    @OneToMany(mappedBy = "airplane")
    private List<Flight> flightList = new ArrayList<>();

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

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
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

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }
}
