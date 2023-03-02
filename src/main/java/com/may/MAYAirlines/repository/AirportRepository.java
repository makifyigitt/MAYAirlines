package com.may.MAYAirlines.repository;

import com.may.MAYAirlines.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer> {

    Optional<Airport> findByAirportCode(String airportCode);

    @Query("select c from Airport c where c.status = true ")
    List<Airport> getAllActiveAirports();
}
