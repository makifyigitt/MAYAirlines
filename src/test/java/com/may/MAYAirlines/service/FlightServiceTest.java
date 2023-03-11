package com.may.MAYAirlines.service;

import com.may.MAYAirlines.entity.Airplane;
import com.may.MAYAirlines.entity.Airport;
import com.may.MAYAirlines.entity.City;
import com.may.MAYAirlines.entity.Flight;
import com.may.MAYAirlines.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {
    private FlightService flightService;
    private FlightRepository flightRepository;
    private AirportService airportService;
    private AirplaneService airplaneService;

    @BeforeEach
    void setUp(){
        this.flightRepository = mock(FlightRepository.class);
        this.airplaneService = mock(AirplaneService.class);
        this.airportService=mock(AirportService.class);
        this.flightService = new FlightService(flightRepository,airplaneService,airportService);
    }
//TODO testlere devam
    @Test
    void getAll() {
        Date date = new Date();
        Flight flight = new Flight();
    }

    @Test
    void findById() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAllActive() {
    }

    @Test
    void getAllReservations() {
    }

    @Test
    void getAllPassengers() {
    }
}