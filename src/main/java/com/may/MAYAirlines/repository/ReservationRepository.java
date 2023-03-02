package com.may.MAYAirlines.repository;


import com.may.MAYAirlines.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    @Query("select c from Reservation c where c.status = true ")
    List<Reservation> getAllReservations();
}
