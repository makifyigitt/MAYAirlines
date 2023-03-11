package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.CityNotFoundException;
import com.may.MAYAirlines.dto.CityDTO;
import com.may.MAYAirlines.entity.City;
import com.may.MAYAirlines.entity.Country;
import com.may.MAYAirlines.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CityServiceTest {
    private CityService cityService;
    private CityRepository cityRepository;
    private AirportService airportService;

    @BeforeEach
    void setUp(){
        cityRepository = Mockito.mock(CityRepository.class);
        airportService=Mockito.mock(AirportService.class);
        cityService = new CityService(cityRepository,airportService);
    }
    @Test
    void getAllCities_shouldReturnCityDTOList() {
        City city = new City("İstanbul",new Country("Turkey","TR"));
        City city1 = new City("Ankara",new Country("Turkey","TR"));
        City city2 = new City("Bursa",new Country("Turkey","TR"));
        City city3 = new City("London",new Country("United Kingdom","UK"));
        List<City> cities = List.of(city,city1,city2,city3);

        List<CityDTO> cityDTOS = cities
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());

        Mockito.when(cityRepository.findAll()).thenReturn(cities);

        List<CityDTO> result = cityService.getAllCities();

        assertEquals(result,cityDTOS);
        Mockito.verify(cityRepository).findAll();
    }

    @Test
    void getAllActiveCities_shouldReturnCityDTOList() {
        City city = new City("İstanbul",new Country("Turkey","TR"));
        City city1 = new City("Ankara",new Country("Turkey","TR"));
        City city2 = new City("Bursa",new Country("Turkey","TR"));
        City city3 = new City("London",new Country("United Kingdom","UK"));
        city.setStatus(false);
        city1.setStatus(false);
        List<City> activeCities = List.of(city3,city2);

        List<CityDTO> cityDTOS = activeCities
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());

        Mockito.when(cityRepository.getAllActiveCities()).thenReturn(activeCities);

        List<CityDTO> result = cityService.getAllActiveCities();

        assertEquals(result,cityDTOS);
        Mockito.verify(cityRepository).getAllActiveCities();
    }

    @Test
    void findById_whenCityIdDoesExist_shouldReturnCity() {
        City city = new City(111,"İstanbul",new Country("Turkey","TR"));

        Mockito.when(cityRepository.findById(111)).thenReturn(Optional.of(city));

        City result = cityService.findById(111);

        assertEquals(result,city);
        Mockito.verify(cityRepository).findById(111);
    }

    @Test
    void findById_whenCityIdDoesNotExist_shouldThrowCityNotFoundException() {
        Mockito.when(cityRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(CityNotFoundException.class,()->cityService.findById(111));
        Mockito.verify(cityRepository).findById(111);
    }

    @Test
    void getById_whenCityIdDoesExist_shouldReturnCityDTO() {
        City city = new City(111,"İstanbul",new Country("Turkey","TR"));
        CityDTO cityDTO = new CityDTO(city);

        Mockito.when(cityRepository.findById(111)).thenReturn(Optional.of(city));

        CityDTO result = cityService.getById(111);

        assertEquals(result,cityDTO);
        Mockito.verify(cityRepository).findById(111);
    }

    @Test
    void getById_whenCityIdDoesNotExist_shouldThrowCityNotFoundException() {
        Mockito.when(cityRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(CityNotFoundException.class,()->cityService.getById(111));
        Mockito.verify(cityRepository).findById(111);
    }

}