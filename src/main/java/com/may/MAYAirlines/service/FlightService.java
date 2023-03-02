package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.AirportNotActiveException;
import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.core.exception.FlightNotFoundException;
import com.may.MAYAirlines.dto.*;
import com.may.MAYAirlines.entity.Customer;
import com.may.MAYAirlines.entity.Flight;
import com.may.MAYAirlines.entity.Reservation;
import com.may.MAYAirlines.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@EnableScheduling
@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirplaneService airplaneService;
    private final AirportService airportService;

    public FlightService(FlightRepository flightRepository, AirplaneService airplaneService, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airplaneService = airplaneService;
        this.airportService = airportService;
    }

    @Scheduled(cron="0 0 */12 * * *", zone="Europe/Istanbul")
    public void controlFlights(){
        List<Flight> flights = flightRepository.getAllActiveFlights()
                .stream()
                .filter(p -> p.getBoardingDate().before(new Date())).collect(Collectors.toList());

        flights.forEach(p->p.setStatus(false));

        flightRepository.saveAll(flights);
    }


    public List<FlightDTO> getAll() {
        List<Flight> flights=flightRepository.findAll();
        return flights.stream()
                .map(FlightDTO::new)
                .collect(Collectors.toList());
    }
    protected Flight findById(int id){
        return flightRepository.findById(id)
                .orElseThrow(()-> new FlightNotFoundException(ErrorCode.FLIGHT_NOT_FOUND));
    }

    public FlightDTO getById(int id) {
        return new FlightDTO(findById(id));
    }

    public List<FlightDTO> getAllActive() {
        return flightRepository.getAllActiveFlights()
                .stream()
                .map(FlightDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(FlightCreateDTO flightCreateDTO) {
        Flight flight = new Flight();
        flight.setAirplane(airplaneService.findById(flightCreateDTO.getAirplaneId()));
        flight.setBoardingDate(flightCreateDTO.getBoardingDate());
        flight.setDuration(flightCreateDTO.getDuration());
        if(airportService.findById(flightCreateDTO.getDepartureAirportId()).isStatus() &&
                airportService.findById(flightCreateDTO.getArrivalAirportId()).isStatus()){
            flight.setDepartureAirport(airportService.findById(flightCreateDTO.getDepartureAirportId()));
            flight.setArrivalAirport(airportService.findById(flightCreateDTO.getArrivalAirportId()));
            flight.setGate(flightCreateDTO.getGate());
            flight.setCapacity(flight.getAirplane().getCapacity());
            flightRepository.save(flight);
        }else{
            throw new AirportNotActiveException(ErrorCode.AIRPORT_NOT_ACTIVE);
        }
    }
    //TODO airport activeliğinin city ve country ile kontrollünü sağla.
    public void update(FlightUpdateDTO flightUpdateDTO) {
        Flight flight = findById(flightUpdateDTO.getId());
        flight.setAirplane(airplaneService.findById(flightUpdateDTO.getAirplaneId()));
        flight.setBoardingDate(flightUpdateDTO.getBoardingDate());
        flight.setDuration(flightUpdateDTO.getDuration());
        flight.setDepartureAirport(airportService.findById(flightUpdateDTO.getDepartureAirportId()));
        flight.setArrivalAirport(airportService.findById(flightUpdateDTO.getArrivalAirportId()));
        flight.setGate(flightUpdateDTO.getGate());
        flight.setUpdateDate(new Date());
        flightRepository.save(flight);
    }
    //Eğer başarılı bir rezervasyon ise true, eğer  başarısız bir rezervasyon false
    protected void changeCapacity(int id, boolean reservationStatus){
        Flight flight = findById(id);
        if (reservationStatus){
            flight.setCapacity(flight.getCapacity()-1);
        }else{
            flight.setCapacity(flight.getCapacity()+1);
        }
        flightRepository.save(flight);
    }

    public void makeInactive(int id){
        Flight flight = findById(id);
        flight.setStatus(false);
        flightRepository.save(flight);
    }

    public void makeActive(int id){
        Flight flight = findById(id);
        flight.setStatus(true);
        flightRepository.save(flight);
    }


    public List<ReservationDTO> getAllReservations(int flightId) {
        Flight flight = findById(flightId);
        return flight.getReservationList()
                .stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    public List<CustomerDTO> getAllPassengers(int flightId) {
        Flight flight = findById(flightId);
        List<Reservation> reservationList = flight.getReservationList();
        return reservationList.stream()
                .map(Reservation::getCustomer)
                .map(CustomerDTO::new)
                .toList();
    }
}
