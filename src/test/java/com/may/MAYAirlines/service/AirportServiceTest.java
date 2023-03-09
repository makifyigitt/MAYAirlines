package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.AirportNotFoundException;
import com.may.MAYAirlines.dto.AirportDTO;
import com.may.MAYAirlines.entity.Airport;
import com.may.MAYAirlines.entity.City;
import com.may.MAYAirlines.entity.Country;
import com.may.MAYAirlines.repository.AirplaneRepository;
import com.may.MAYAirlines.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AirportServiceTest {

    private AirportService airportService;
    private AirportRepository airportRepository;

    @BeforeEach
    void setUp() {
        airportRepository = Mockito.mock(AirportRepository.class);
        airportService = new AirportService(airportRepository);
    }

    @Test
    void getAllAirports_shouldReturnAirportDTOList() {
        Airport airport = new Airport("Sabiha Gökçen","SAW",new City("İstanbul",new Country("Türkiye","TR")));
        Airport airport1 = new Airport("İstanbul","IST",new City("İstanbul",new Country("Türkiye","TR")));
        Airport airport2 = new Airport("Adnan Menderes","ADB",new City("İzmir",new Country("Türkiye","TR")));
        List<Airport> airports = List.of(airport,airport1,airport2);

        List<AirportDTO> airportDTOS = airports
                .stream()
                .map(AirportDTO::new)
                .collect(Collectors.toList());

        Mockito.when(airportRepository.findAll())
                .thenReturn(airports);

        List<AirportDTO> result = airportService.getAllAirports();

        assertEquals(result,airportDTOS);
        Mockito.verify(airportRepository).findAll();
    }

    @Test
    void findById_whenAirportIdDoesExist_shouldReturnAirport() {
        Airport airport = new Airport(111,"Sabiha Gökçen","SAW",new City("İstanbul",new Country("Türkiye","TR")));

        Mockito.when(airportRepository.findById(111)).thenReturn(Optional.of(airport));

        Airport result = airportService.findById(111);

        assertEquals(result,airport);
        Mockito.verify(airportRepository).findById(111);
    }

    @Test
    void findById_whenAirportIdDoesNotExist_shouldThrowAirportNotFoundException() {
        Mockito.when(airportRepository.findById(111)).thenReturn(Optional.empty());

       assertThrows(AirportNotFoundException.class,()->airportService.findById(111));
        Mockito.verify(airportRepository).findById(111);
    }

    @Test
    void findByCode_whenAirportCodeDoesExist_shouldReturnAirport() {
        Airport airport = new Airport("Sabiha Gökçen","SAW",new City("İstanbul",new Country("Türkiye","TR")));

        Mockito.when(airportRepository.findByAirportCode("SAW")).thenReturn(Optional.of(airport));

        Airport result = airportService.findByCode("SAW");

        assertEquals(result,airport);
        Mockito.verify(airportRepository).findByAirportCode("SAW");
    }

    @Test
    void findByCode_whenAirportCodeDoesNotExist_shouldThrowAirportNotFoundException() {
        Mockito.when(airportRepository.findByAirportCode("SAW")).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class,()->airportService.findByCode("SAW"));
        Mockito.verify(airportRepository).findByAirportCode("SAW");
    }

    @Test
    void getById_whenAirportIdDoesExist_shouldReturnAirportDTO() {
        Airport airport = new Airport(111,"Sabiha Gökçen","SAW",new City("İstanbul",new Country("Türkiye","TR")));
        AirportDTO airportDTO = new AirportDTO(airport);
        Mockito.when(airportRepository.findById(111)).thenReturn(Optional.of(airport));

        AirportDTO result = airportService.getById(111);

        assertEquals(result,airportDTO);
        Mockito.verify(airportRepository).findById(111);
    }

    @Test
    void getById_whenAirportIdDoesNotExist_shouldThrowAirportNotFoundException() {
        Mockito.when(airportRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class,()->airportService.getById(111));
        Mockito.verify(airportRepository).findById(111);
    }

    @Test
    void getByCode_whenAirportCodeDoesExist_shouldReturnAirportDTO() {
        Airport airport = new Airport("Sabiha Gökçen","SAW",new City("İstanbul",new Country("Türkiye","TR")));
        AirportDTO airportDTO = new AirportDTO(airport);
        Mockito.when(airportRepository.findByAirportCode("SAW")).thenReturn(Optional.of(airport));

        AirportDTO result = airportService.getByCode("SAW");

        assertEquals(result,airportDTO);
        Mockito.verify(airportRepository).findByAirportCode("SAW");
    }

    @Test
    void getByCode_whenAirportCodeDoesNotExist_shouldThrowAirportNotFoundException() {
        Mockito.when(airportRepository.findByAirportCode("SAW")).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class,()->airportService.getByCode("SAW"));
        Mockito.verify(airportRepository).findByAirportCode("SAW");
    }

    @Test
    void getAllActive_shouldReturnAirportDTOList() {
        Airport airport = new Airport("Sabiha Gökçen","SAW",new City("İstanbul",new Country("Türkiye","TR")));
        Airport airport1 = new Airport("İstanbul","IST",new City("İstanbul",new Country("Türkiye","TR")));
        Airport airport2 = new Airport("Adnan Menderes","ADB",new City("İzmir",new Country("Türkiye","TR")));
        Airport airport3 = new Airport("Ankara Esenboğa","ESB",new City("Ankara",new Country("Türkiye","TR")));
        airport3.setStatus(false);
        airport2.setStatus(false);
        List<Airport> activeAirports = List.of(airport,airport1);

        List<AirportDTO> airportDTOS = activeAirports
                .stream()
                .map(AirportDTO::new)
                .collect(Collectors.toList());

        Mockito.when(airportRepository.getAllActiveAirports())
                .thenReturn(activeAirports);

        List<AirportDTO> result = airportService.getAllActive();

        assertEquals(result,airportDTOS);
        Mockito.verify(airportRepository).getAllActiveAirports();
    }
}