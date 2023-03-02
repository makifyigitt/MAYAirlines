package com.may.MAYAirlines.dto;



import com.may.MAYAirlines.entity.Flight;

import java.sql.Time;
import java.util.Date;

public class FlightDTO {
    private int id;
    private AirplaneDTO airplane;
    private Date boardingDate;
    private Time duration;
    private AirportDTO departureAirport;
    private AirportDTO arrivalAirport;


    public FlightDTO(Flight flight){
        this.id=flight.getId();
        this.airplane= new AirplaneDTO(flight.getAirplane());
        this.boardingDate=flight.getBoardingDate();
        this.duration=flight.getDuration();
        this.departureAirport=new AirportDTO(flight.getDepartureAirport());
        this.arrivalAirport = new AirportDTO(flight.getArrivalAirport());
    }
}
