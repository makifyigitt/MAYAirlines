package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.CustomerNotFoundException;
import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.dto.CityDTO;
import com.may.MAYAirlines.entity.City;
import com.may.MAYAirlines.entity.Country;
import com.may.MAYAirlines.repository.CityRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final AirportService airportService;


    public CityService(CityRepository cityRepository, AirportService airportService) {
        this.cityRepository = cityRepository;
        this.airportService = airportService;
    }


    public List<CityDTO> getAllCities(){
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    public List<CityDTO> getAllActiveCities(){
        List<City> cities = cityRepository.getAllActiveCities();
        return cities.stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    protected  City findById(int id){
        return cityRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(ErrorCode.CITY_NOT_FOUND));
    }

    public CityDTO getById(int id){
        return new CityDTO(findById(id));
    }

    public void addCity( String name, Country country){
        City city = new City(name,country);
        cityRepository.save(city);
    }

    public void updateCity(int id, String name, Country country){
        City city = findById(id);
        city.setName(name);
        city.setCountry(country);
        city.setUpdateDate(new Date());
        cityRepository.save(city);
    }

    @Transactional
    public void makeActiveCity(int id){
        City city = findById(id);
        city.setStatus(true);
        cityRepository.save(city);
        city.getAirportList()
                .forEach(p-> airportService.makeActive(p.getId()));
    }
    @Transactional
    public void makeInactiveCity(int id){
        City city = findById(id);
        city.setStatus(false);
        cityRepository.save(city);
        city.getAirportList()
                .forEach(p-> airportService.makeInactive(p.getId()));
    }


}
