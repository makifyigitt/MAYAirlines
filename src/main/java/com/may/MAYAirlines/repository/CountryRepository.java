package com.may.MAYAirlines.repository;

import com.may.MAYAirlines.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {
    @Query("select c from Country c where c.status = true ")
    List<Country> getAllActiveCountries();

    Optional<Country> findByCountryCode(String countryCode);

    @Query("select c from Country c where c.status = false ")
    List<Country> getAllInactiveCountries();
}
