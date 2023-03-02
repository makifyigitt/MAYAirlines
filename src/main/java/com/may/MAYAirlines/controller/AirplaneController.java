package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.AirplaneDTO;
import com.may.MAYAirlines.service.AirplaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AirplaneDTO>> getAll(){
        return ResponseEntity.ok(airplaneService.getAll());
    }

    @GetMapping("/allactive")
    public ResponseEntity<List<AirplaneDTO>> getAllActive(){
        return ResponseEntity.ok(airplaneService.getAllActive());
    }

    @GetMapping()
    public ResponseEntity<AirplaneDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(airplaneService.getById(id));
    }

    @PatchMapping("/active")
    public BaseResponse makeActive(@RequestParam int id){
        airplaneService.makeActive(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping
    public BaseResponse makeInactive(@RequestParam int id){
        airplaneService.makeInactive(id);
        return new BaseResponse(Response.DELETE);
    }

    @PostMapping("/create")
    public BaseResponse create(@RequestParam String planeType,
                               @RequestParam int capacity){
        airplaneService.create(planeType,capacity);
        return new BaseResponse(Response.CREATE);
    }

    @PutMapping("/update")
    public BaseResponse update(@RequestParam int id,@RequestParam int capacity){
        airplaneService.update(id,capacity);
        return new BaseResponse(Response.UPDATE);
    }
}
