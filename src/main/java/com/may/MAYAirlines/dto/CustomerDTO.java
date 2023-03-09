package com.may.MAYAirlines.dto;

import com.may.MAYAirlines.entity.Customer;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record CustomerDTO (
        Integer id,
        String username,
        String firstName,
        String surname,
        String email,
        String phoneNumber,
        String address,
        boolean status,
        List<ReservationDTO> reservationList,
        Date createDate,
        Date updateDate

){

}
