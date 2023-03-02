package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.AirportDTO;
import com.may.MAYAirlines.entity.City;
import com.may.MAYAirlines.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
    @GetMapping("/getall")
    public ResponseEntity<List<AirportDTO>> getAll(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping()
    public ResponseEntity<AirportDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(airportService.getById(id));
    }

    @GetMapping("/code")
    public ResponseEntity<AirportDTO> getByCode(@RequestParam("code") String code){
        return ResponseEntity.ok(airportService.getByCode(code));
    }

    @GetMapping("/allactive")
    public ResponseEntity<List<AirportDTO>> getAllActive(){
        return ResponseEntity.ok(airportService.getAllActive());
    }

    @PatchMapping("/active")
    public BaseResponse makeActive(@RequestParam("id") int id){
        airportService.makeActive(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping("/delete")
    public BaseResponse makeInactive(@RequestParam int id){
        airportService.makeInactive(id);
        return new BaseResponse(Response.DELETE);
    }

    @PostMapping()
    public BaseResponse addAirport(@RequestParam("name") String name,
                                   @RequestParam("code") String airportCode,
                                   @RequestParam("city") City city){
        airportService.addAirport(name,airportCode,city);
        return new BaseResponse(Response.ADD);
    }

    @PutMapping
    public BaseResponse updateAirport(@RequestParam int id,
                                      @RequestParam String name,
                                      @RequestParam("code") String airportCode,
                                      @RequestParam("city") City city){
        airportService.updateAirport(id, name, airportCode, city);
        return new BaseResponse(Response.UPDATE);
    }

}
