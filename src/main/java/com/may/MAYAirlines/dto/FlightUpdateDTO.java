package com.may.MAYAirlines.dto;

import java.sql.Time;
import java.util.Date;

public class FlightUpdateDTO {
    private int id;
    private int airplaneId;
    private Date boardingDate;
    private Time duration;
    private int departureAirportId;
    private int arrivalAirportId;
    private String gate;

    public int getId() {
        return id;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    public Date getBoardingDate() {
        return boardingDate;
    }

    public Time getDuration() {
        return duration;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public int getArrivalAirportId() {
        return arrivalAirportId;
    }

    public String getGate() {
        return gate;
    }


}
