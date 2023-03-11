package com.may.MAYAirlines.service;


import com.may.MAYAirlines.core.exception.CountryCodeExistException;
import com.may.MAYAirlines.core.exception.CountryNotFoundException;
import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.dto.CountryDTO;
import com.may.MAYAirlines.entity.Country;
import com.may.MAYAirlines.repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final CityService cityService;


    public CountryService(CountryRepository countryRepository, CityService cityService) {
        this.countryRepository = countryRepository;
        this.cityService = cityService;
    }

    public List<CountryDTO> getAllCountries(){
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());
    }

    public List<CountryDTO> getAllActiveCountries(){
        List<Country> countries = countryRepository.getAllActiveCountries();
        return countries.stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());
    }

    protected Country findById(int id){
        return countryRepository.findById(id)
                .orElseThrow(()-> new CountryNotFoundException(ErrorCode.COUNTRY_NOT_FOUND));
    }

    public CountryDTO getById(int id){
        return new CountryDTO(findById(id));
    }

    protected Country findByCountryCode(String countryCode){
        return countryRepository.findByCountryCode(countryCode)
                .orElseThrow(()-> new CountryNotFoundException(ErrorCode.COUNTRY_NOT_FOUND));
    }
    public CountryDTO getByCountryCode(String countryCode){
        return new CountryDTO(findByCountryCode(countryCode));
    }

    public void addCountry(String name, String countryCode){
        if (findByCountryCode(countryCode)!= null){
            throw new CountryCodeExistException(ErrorCode.COUNTRY_CODE_IS_EXIST);
        }else{
            Country country = new Country(name,countryCode);
            countryRepository.save(country);
        }
    }

    public void updateCountry(int id,String name, String countryCode){
        Country country = findById(id);
        country.setName(name);
        country.setCountryCode(countryCode);
        country.setUpdateDate(new Date());
        countryRepository.save(country);
    }

    @Transactional
    public void makeActiveCountry(int id){
        Country country = findById(id);
        country.setStatus(true);
        countryRepository.save(country);
        country.getCities()
                .forEach(k-> cityService.makeActiveCity(k.getId()));

    }
    @Transactional
    public void makeInactiveCountry(int id){
        Country country = findById(id);
        country.setStatus(false);
        countryRepository.save(country);
        country.getCities()
                .forEach(k-> cityService.makeInactiveCity(k.getId()));
    }

    public List<CountryDTO> getAllInactiveCountries() {
        return countryRepository.getAllInactiveCountries()
                .stream()
                .map(CountryDTO::new)
                .collect(Collectors.toList());
    }
}
