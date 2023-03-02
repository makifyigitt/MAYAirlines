package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.CountryDTO;
import com.may.MAYAirlines.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private final CountryService countryService;


    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CountryDTO>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping()
    public ResponseEntity<CountryDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(countryService.getById(id));
    }

    @GetMapping("/code")
    public ResponseEntity<CountryDTO> getByCountryCode(@RequestParam("code") String code){
        return ResponseEntity.ok(countryService.getByCountryCode(code));
    }

    @GetMapping("active")
    public ResponseEntity<List<CountryDTO>> getAllActiveCountries(){
        return ResponseEntity.ok(countryService.getAllActiveCountries());
    }

    @GetMapping("inactive")
    public ResponseEntity<List<CountryDTO>> getAllInactiveCountries(){
        return ResponseEntity.ok(countryService.getAllInactiveCountries());
    }

    @PatchMapping
    public BaseResponse makeActiveCountry(@RequestParam int id){
        countryService.makeActiveCountry(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping
    public BaseResponse makeInactiveCountry(@RequestParam int id){
        countryService.makeInactiveCountry(id);
        return new BaseResponse(Response.DELETE);
    }

    @PutMapping
    public BaseResponse updateCountry(@RequestParam int id,
                                      @RequestParam String name,
                                      @RequestParam("code") String countryCode){
        countryService.updateCountry(id, name, countryCode);
        return new BaseResponse(Response.UPDATE);
    }

    @PostMapping
    public BaseResponse addCountry(@RequestParam String name,
                                   @RequestParam("code") String countryCode){
        countryService.addCountry(name, countryCode);
        return new BaseResponse(Response.ADD);
    }


}
