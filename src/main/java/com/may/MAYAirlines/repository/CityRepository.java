package com.may.MAYAirlines.repository;

import com.may.MAYAirlines.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    @Query("select c from City c where c.status = true ")
    List<City> getAllActiveCities();
}
