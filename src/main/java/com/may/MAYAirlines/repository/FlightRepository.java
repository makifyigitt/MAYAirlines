package com.may.MAYAirlines.repository;

import com.may.MAYAirlines.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
    @Query("select c from Flight c where c.status = true")
    List<Flight> getAllActiveFlights();
}
