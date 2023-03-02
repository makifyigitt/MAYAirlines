package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.AirportCodeExistException;
import com.may.MAYAirlines.core.exception.AirportNotFoundException;
import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.dto.AirportDTO;
import com.may.MAYAirlines.entity.Airport;
import com.may.MAYAirlines.entity.City;
import com.may.MAYAirlines.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository airportRepository;


    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> getAllAirports(){
        return airportRepository.findAll()
                .stream()
                .map(AirportDTO::new)
                .collect(Collectors.toList());
    }

    protected Airport findById(int id){
        return airportRepository.findById(id)
                .orElseThrow(()->new AirportNotFoundException(ErrorCode.AIRPORT_NOT_FOUND));
    }
    protected Airport findByCode(String airportCode){
        return airportRepository.findByAirportCode(airportCode)
                .orElseThrow(()-> new AirportNotFoundException(ErrorCode.AIRPORT_NOT_FOUND));
    }

    public AirportDTO getById(int id){
        return new AirportDTO(findById(id));
    }

    public AirportDTO getByCode(String code){
        return new AirportDTO(findByCode(code));
    }

    public List<AirportDTO> getAllActive(){
        return airportRepository.getAllActiveAirports()
                .stream()
                .map(AirportDTO::new)
                .collect(Collectors.toList());
    }

    public void makeActive(int id){
        Airport airport = findById(id);
        airport.setStatus(true);
        airportRepository.save(airport);
    }

    public void makeInactive(int id){
        Airport airport = findById(id);
        airport.setStatus(false);
        airportRepository.save(airport);
    }

    public void addAirport(String name, String airportCode, City city){
        if (findByCode(airportCode)!= null){
            throw new AirportCodeExistException(ErrorCode.AIRPORT_CODE_IS_EXIST);
        }else{
            Airport airport = new Airport(name,airportCode,city);
            airportRepository.save(airport);
        }
    }

    public void updateAirport(int id,String name,String airportCode,City city){
        Airport airport = findById(id);
        airport.setName(name);
        airport.setAirportCode(airportCode);
        airport.setCity(city);
        airport.setUpdateDate(new Date());
    }

}
