package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReservationDTOMapper implements Function<Reservation,ReservationDTO> {
    @Override
    public ReservationDTO apply(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getCustomer().getId(),
                reservation.getFlight().getId(),
                reservation.getSeatNo(),
                reservation.getCreateDate(),
                reservation.getUpdateDate(),
                reservation.isStatus());
    }
}
