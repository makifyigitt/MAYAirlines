package com.may.MAYAirlines.repository;

import com.may.MAYAirlines.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Integer> {
    @Query("select c from Airplane c where c.status = true")
    List<Airplane> getAllActiveAirplanes();
}
