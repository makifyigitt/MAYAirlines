package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.AirplaneNotFoundException;
import com.may.MAYAirlines.dto.AirplaneDTO;
import com.may.MAYAirlines.entity.Airplane;
import com.may.MAYAirlines.repository.AirplaneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneServiceTest {
    private AirplaneService airplaneService;
    private AirplaneRepository airplaneRepository;

    @BeforeEach
    void setUp(){
        airplaneRepository = Mockito.mock(AirplaneRepository.class);
        airplaneService = new AirplaneService(airplaneRepository);
    }

    @Test
    void findById_whenAirplaneIdDoesExist_shouldReturnAirplane() {
        Airplane airplane = new Airplane(111,"Airbus A350-900",300);

        Mockito.when(airplaneRepository.findById(111)).thenReturn(Optional.of(airplane));

        Airplane result = airplaneService.findById(111);

        assertEquals(airplane,result);
    }

    @Test
    void findById_whenAirplaneIdDoesNotExist_shouldThrowAirplaneNotFoundException() {

        Mockito.when(airplaneRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(AirplaneNotFoundException.class,()->airplaneService.findById(111));
        Mockito.verify(airplaneRepository).findById(111);
    }

    @Test
    void getById_whenAirplaneIdDoesExist_shouldReturnAirplaneDTO() {
        Airplane airplane = new Airplane(111,"Airbus A350-900",300);
        AirplaneDTO airplaneDTO = new AirplaneDTO(airplane);

        Mockito.when(airplaneRepository.findById(111)).thenReturn(Optional.of(airplane));

        AirplaneDTO result = airplaneService.getById(111);

        Mockito.verify(airplaneRepository).findById(111);
        assertEquals(airplaneDTO,result);
    }

    @Test
    void getById_whenAirplaneIdDoesNotExist_shouldThrowAirplaneNotFoundException() {
        Mockito.when(airplaneRepository.findById(111)).thenReturn(Optional.empty());

        assertThrows(AirplaneNotFoundException.class,()->airplaneService.getById(111));
        Mockito.verify(airplaneRepository).findById(111);
    }

    @Test
    void getAll_shouldReturnAirplaneDTOList() {
        Airplane airplane = new Airplane("planetype",200);
        Airplane airplane1 = new Airplane("planetype1",300);
        Airplane airplane2 = new Airplane("planetype2",400);
        List<Airplane> airplaneList = List.of(airplane,airplane1,airplane2);

        List<AirplaneDTO> airplaneDTOS = airplaneList.stream()
                .map(AirplaneDTO::new)
                .collect(Collectors.toList());


        Mockito.when(airplaneRepository.findAll())
                .thenReturn(airplaneList);

        List<AirplaneDTO> result = airplaneService.getAll();

        Mockito.verify(airplaneRepository).findAll();
        assertEquals(result,airplaneDTOS);
    }

    @Test
    void getAllActive_shouldReturnAirplaneDTOList() {
        Airplane airplane = new Airplane("planetype",200);
        Airplane airplane1 = new Airplane("planetype1",300);
        Airplane airplane2 = new Airplane("planetype2",400);
        Airplane airplane3 = new Airplane("planetype3",500);
        airplane3.setStatus(false);
        airplane2.setStatus(false);
        List<Airplane> activeAirplaneList = List.of(airplane2,airplane3);

        List<AirplaneDTO> airplaneDTOS = activeAirplaneList.stream()
                .map(AirplaneDTO::new)
                .collect(Collectors.toList());


        Mockito.when(airplaneRepository.getAllActiveAirplanes())
                .thenReturn(activeAirplaneList);

        List<AirplaneDTO> result = airplaneService.getAllActive();

        Mockito.verify(airplaneRepository).getAllActiveAirplanes();
        assertEquals(result,airplaneDTOS);
    }
}