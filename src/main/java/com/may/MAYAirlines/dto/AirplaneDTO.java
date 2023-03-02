package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Airplane;

public class AirplaneDTO {
    private int id;
    private String planeType;
    private int capacity;

    public AirplaneDTO(Airplane airplane){
        this.id=airplane.getId();
        this.planeType=airplane.getPlaneType();
        this.capacity= airplane.getCapacity();
    }

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
}
