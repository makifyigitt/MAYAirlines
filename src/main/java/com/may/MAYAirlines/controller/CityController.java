package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.CityDTO;
import com.may.MAYAirlines.entity.Country;
import com.may.MAYAirlines.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CityDTO>> getAllCities(){
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping()
    public ResponseEntity<CityDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(cityService.getById(id));
    }

    @GetMapping("active")
    public ResponseEntity<List<CityDTO>> getAllActiveCities(){
        return ResponseEntity.ok(cityService.getAllActiveCities());
    }

    @PatchMapping("active")
    public BaseResponse makeActive(@RequestParam int id){
        cityService.makeActiveCity(id);
        return new BaseResponse(Response.ACTIVE);
    }
    @DeleteMapping
    public BaseResponse makeInactive(@RequestParam int id){
        cityService.makeInactiveCity(id);
        return new BaseResponse(Response.DELETE);
    }

    @PostMapping
    public BaseResponse addCity(@RequestParam String name,
                                @RequestParam("country") Country country){
        cityService.addCity(name, country);
        return new BaseResponse(Response.ADD);
    }

    @PutMapping
    public BaseResponse updateCity(@RequestParam int id,
                                   @RequestParam String name,
                                   @RequestParam("country")Country country){
        cityService.updateCity(id, name, country);
        return new BaseResponse(Response.UPDATE);
    }

}
