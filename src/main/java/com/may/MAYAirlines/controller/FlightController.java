package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.*;
import com.may.MAYAirlines.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<FlightDTO>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAll());
    }

    @GetMapping()
    public ResponseEntity<FlightDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(flightService.getById(id));
    }

    @GetMapping("/allavtive")
    public ResponseEntity<List<FlightDTO>> getAllActiveFlights(){
        return ResponseEntity.ok(flightService.getAllActive());
    }

    @PostMapping("/create")
    public BaseResponse createFlight(@RequestBody FlightCreateDTO flightCreateDTO){
        flightService.create(flightCreateDTO);
        return new BaseResponse(Response.CREATE);
    }

    @PutMapping("/update")
    public BaseResponse updateFlight(@RequestBody FlightUpdateDTO flightUpdateDTO){
        flightService.update(flightUpdateDTO);
        return new BaseResponse(Response.UPDATE);
    }

    @PatchMapping("/active")
    public BaseResponse makeActive(@RequestParam int id){
        flightService.makeActive(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping("/inactive")
    public BaseResponse makeInactive(@RequestParam int id){
        flightService.makeInactive(id);
        return new BaseResponse(Response.DELETE);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(int flightId){
        return ResponseEntity.ok(flightService.getAllReservations(flightId));
    }

    @GetMapping("/passengers")
    public ResponseEntity<List<CustomerDTO>> getAllPassengers(int flightId){
        return ResponseEntity.ok(flightService.getAllPassengers(flightId));
    }

}
