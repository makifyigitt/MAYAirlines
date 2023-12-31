package com.may.MAYAirlines.service;

import com.may.MAYAirlines.core.exception.ErrorCode;
import com.may.MAYAirlines.core.exception.ReservationNotFound;
import com.may.MAYAirlines.dto.ReservationDTO;
import com.may.MAYAirlines.dto.ReservationDTOMapper;
import com.may.MAYAirlines.entity.Reservation;
import com.may.MAYAirlines.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository repository;
    private final CustomerService customerService;
    private final FlightService flightService;
    private final ReservationDTOMapper reservationDTOMapper;


    public ReservationService(ReservationRepository reservationRepository,
                              CustomerService customerService,
                              FlightService flightService, ReservationDTOMapper mapper) {
        this.repository = reservationRepository;
        this.customerService = customerService;
        this.flightService = flightService;
        this.reservationDTOMapper = mapper;
    }


    public List<ReservationDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(reservationDTOMapper)
                .collect(Collectors.toList());
    }


    public List<ReservationDTO> getAllActive() {
        return repository.getAllReservations()
                .stream()
                .map(reservationDTOMapper)
                .collect(Collectors.toList());
    }

    public Reservation findById(int id){
        return repository.findById(id)
                .orElseThrow(()-> new ReservationNotFound(ErrorCode.RESERVATION_NOT_FOUND));
    }


    public ReservationDTO getById(int id) {
        return reservationDTOMapper.apply(findById(id));
    }



    @Transactional
    public void makeActive(int id) {
        Reservation reservation = findById(id);
        reservation.setStatus(true);
        flightService.changeCapacity(reservation.getFlight().getId(),reservation.isStatus());
        repository.save(reservation);
    }

    @Transactional
    public void makeInactive(int id) {
        Reservation reservation = findById(id);
        reservation.setStatus(false);
        flightService.changeCapacity(reservation.getFlight().getId(),reservation.isStatus());
        repository.save(reservation);

    }

    @Transactional
    public void add(int customerId,
                    int flightId,
                    String seatNo) {
        Reservation reservation = new Reservation(customerService.findById(customerId),
                flightService.findById(flightId),
                seatNo);
        flightService.changeCapacity(flightId,reservation.isStatus());
        repository.save(reservation);
    }

    public void update(int id,
                       int flightId,
                       String seatNo) {
        Reservation reservation = findById(id);
        reservation.setFlight(flightService.findById(flightId));
        reservation.setSeatNo(seatNo);
        reservation.setUpdateDate(new Date());
        repository.save(reservation);
    }
}
