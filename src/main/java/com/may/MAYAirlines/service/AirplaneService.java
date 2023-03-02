package com.may.MAYAirlines.service;


import com.may.MAYAirlines.core.exception.AirplaneNotFoundException;
import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.dto.AirplaneDTO;
import com.may.MAYAirlines.entity.Airplane;
import com.may.MAYAirlines.repository.AirplaneRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@EnableScheduling
@Service
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

//TODO Bu kısımda bir güncelleme yapmalısın.
    public void maintenanceControl(){
        List<Airplane> airplanes = airplaneRepository.getAllActiveAirplanes();
        airplanes.stream().filter(k->k.getMaintenanceDate().before(new Date())).toList();
        airplanes.forEach(k->k.setStatus(false));
        airplaneRepository.saveAll(airplanes);
    }

    protected Airplane findById(int id){
        return airplaneRepository.findById(id)
                .orElseThrow(()-> new AirplaneNotFoundException(ErrorCode.AIRPLANE_NOT_FOUND));
    }

    public AirplaneDTO getById(int id){
        return new AirplaneDTO(findById(id));
    }

    public List<AirplaneDTO> getAll() {
        return airplaneRepository.findAll()
                .stream()
                .map(AirplaneDTO::new)
                .toList();
    }

    public List<AirplaneDTO> getAllActive() {
        return airplaneRepository.getAllActiveAirplanes()
                .stream()
                .map(AirplaneDTO::new)
                .toList();
    }

    public void makeActive(int id) {
        Airplane airplane = findById(id);
        airplane.setStatus(true);
        airplaneRepository.save(airplane);
    }

    public void makeInactive(int id) {
        Airplane airplane = findById(id);
        airplane.setStatus(false);
        airplaneRepository.save(airplane);
    }

    public void create(String planeType, int capacity) {
        Airplane airplane = new Airplane(planeType,capacity);
        airplaneRepository.save(airplane);
    }

    public void update(int id, int capacity) {
        Airplane airplane = findById(id);
        airplane.setCapacity(capacity);
        airplaneRepository.save(airplane);
    }
}
