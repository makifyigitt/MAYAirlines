package com.may.MAYAirlines.controller;

import com.may.MAYAirlines.core.BaseResponse;
import com.may.MAYAirlines.core.Response;
import com.may.MAYAirlines.dto.ReservationDTO;
import com.may.MAYAirlines.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ReservationDTO>> getAll(){
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ReservationDTO>> getAllActive(){
        return ResponseEntity.ok(reservationService.getAllActive());
    }

    @GetMapping()
    public ResponseEntity<ReservationDTO> getById(@RequestParam int id){
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @PatchMapping
    public BaseResponse makeActive(@RequestParam int id){
        reservationService.makeActive(id);
        return new BaseResponse(Response.ACTIVE);
    }

    @DeleteMapping
    public BaseResponse makeInactive(@RequestParam int id){
        reservationService.makeInactive(id);
        return new BaseResponse(Response.DELETE);
    }

    @PostMapping
    public BaseResponse createReservation(@RequestParam("customer") int customerId,
                                          @RequestParam("flight") int flightId,
                                          @RequestParam("seat") String seatNo){
        reservationService.add(customerId,flightId,seatNo);
        return new BaseResponse(Response.ADD);
    }


    @PutMapping
    public BaseResponse updateReservation(@RequestParam int id,
                                          @RequestParam("flight") int flightId,
                                          @RequestParam("seat") String seatNo){
        reservationService.update(id,flightId,seatNo);
        return new BaseResponse(Response.UPDATE);
    }


}
