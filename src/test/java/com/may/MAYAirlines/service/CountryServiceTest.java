package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.CountryNotFoundException;
import com.may.MAYAirlines.dto.CountryDTO;
import com.may.MAYAirlines.entity.Country;
import com.may.MAYAirlines.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CountryServiceTest {
    private CountryRepository countryRepository;
    private CityService cityService;
    private CountryService countryService;
    @BeforeEach
    void setUp(){
        cityService=mock(CityService.class);
        countryRepository= mock(CountryRepository.class);
        countryService = new CountryService(countryRepository,cityService);
    }

    @Test
    void getAllCountries_shouldReturnCountriesDTOList() {
        Country country = new Country("Turkey","TR");
        Country country1 = new Country("United Kingdom","UK");
        Country country2 = new Country("United State of America","USA");
        List<Country> countries = List.of(country1,country2,country);
        List<CountryDTO> countryDTOS = countries
                .stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());

        when(countryRepository.findAll()).thenReturn(countries);

        List<CountryDTO> result = countryService.getAllCountries();

        assertEquals(result,countryDTOS);
        verify(countryRepository).findAll();
    }

    @Test
    void getAllActiveCountries_shouldReturnCountryDTOList() {
        Country country = new Country("Turkey","TR");
        Country country1 = new Country("United Kingdom","UK");
        Country country2 = new Country("United State of America","USA");
        country2.setStatus(false);
        country1.setStatus(false);
        List<Country> activeCountries = List.of(country);
        List<CountryDTO> countryDTOS = activeCountries
                .stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());

        when(countryRepository.getAllActiveCountries()).thenReturn(activeCountries);

        List<CountryDTO> result = countryService.getAllActiveCountries();

        assertEquals(result,countryDTOS);
        verify(countryRepository).getAllActiveCountries();
    }

    @Test
    void findById_whenCountryIdDoesExist_shouldReturnCountry() {
        Country country = new Country(111,"Turkey","TR");

        when(countryRepository.findById(111)).thenReturn(Optional.of(country));

        Country result = countryService.findById(111);

        assertEquals(result,country);
        verify(countryRepository).findById(111);
    }

    @Test
    void findById_whenCountryIdDoesNotExist_shouldThrowCountryNotFoundException() {
        when(countryRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(CountryNotFoundException.class,()->countryService.findById(111));
        verify(countryRepository).findById(111);
    }
    @Test
    void getById_whenCountryIdDoesExist_shouldReturnCountryDTO() {
        Country country = new Country(111,"Turkey","TR");
        CountryDTO countryDTO = new CountryDTO(country);

        when(countryRepository.findById(111)).thenReturn(Optional.of(country));

        CountryDTO result = countryService.getById(111);

        assertEquals(result,countryDTO);
        verify(countryRepository).findById(111);
    }

    @Test
    void getById_whenCountryIdDoesNotExist_shouldThrowCountryNotFoundException() {
        when(countryRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(CountryNotFoundException.class,()->countryService.getById(111));
        verify(countryRepository).findById(111);
    }

    @Test
    void findByCountryCode_whenCountryCodeDoesExist_shouldReturnCountry() {
        Country country = new Country(111,"Turkey","TR");

        when(countryRepository.findByCountryCode("TR")).thenReturn(Optional.of(country));

        Country result = countryService.findByCountryCode("TR");

        assertEquals(result,country);
        verify(countryRepository).findByCountryCode("TR");
    }

    @Test
    void findByCountryCode_whenCountryCodeDoesNotExist_shouldThrowCountryNotFoundException() {
        when(countryRepository.findByCountryCode("TR")).thenReturn(Optional.empty());

        assertThrows(CountryNotFoundException.class,()->countryService.findByCountryCode("TR"));
        verify(countryRepository).findByCountryCode("TR");
    }

    @Test
    void getByCountryCode_whenCountryCodeDoesExist_shouldReturnCountryDTO() {
        Country country = new Country(111,"Turkey","TR");
        CountryDTO countryDTO = new CountryDTO(country);

        when(countryRepository.findByCountryCode("TR")).thenReturn(Optional.of(country));

        CountryDTO result = countryService.getByCountryCode("TR");

        assertEquals(result,countryDTO);
        verify(countryRepository).findByCountryCode("TR");
    }

    @Test
    void getByCountryCode_whenCountryCodeDoesNotExist_shouldThrowCountryNotFoundException() {
        when(countryRepository.findByCountryCode("TR")).thenReturn(Optional.empty());

        assertThrows(CountryNotFoundException.class,()->countryService.getByCountryCode("TR"));
        verify(countryRepository).findByCountryCode("TR");
    }
    @Test
    void getAllInactiveCountries_shouldReturnCountryDTOList() {
        Country country = new Country("Turkey","TR");
        Country country1 = new Country("United Kingdom","UK");
        Country country2 = new Country("United State of America","USA");
        country2.setStatus(false);
        country1.setStatus(false);
        List<Country> inactiveCountries = List.of(country2,country1);
        List<CountryDTO> countryDTOS = inactiveCountries
                .stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());

        when(countryRepository.getAllInactiveCountries()).thenReturn(inactiveCountries);

        List<CountryDTO> result = countryService.getAllInactiveCountries();

        assertEquals(result,countryDTOS);
        verify(countryRepository).getAllInactiveCountries();
    }
}