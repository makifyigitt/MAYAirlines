package com.may.MAYAirlines.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {
    public Flight() {
        this.reservationList=new ArrayList<>();
    }

    public Flight(Airplane airplane,
                  Date boardingDate,
                  Time duration,
                  Airport departureAirport,
                  Airport arrivalAirport,
                  String gate) {
        this.airplane = airplane;
        this.boardingDate = boardingDate;
        this.duration = duration;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.gate = gate;
        this.reservationList = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "airplane_id",nullable = false)
    private Airplane airplane;

    @NotNull
    @Column(name = "boarding_date")
    private Date boardingDate;

    @NotNull
    @Column(name = "duration")
    private Time duration;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "flight")
    private List<Reservation> reservationList = new ArrayList<>();

    @NotNull
    @Size(max = 3)
    @Column(name = "gate")
    private String gate;

    @Column(name = "create_date")
    private Date createDate = new Date();
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "status")
    private boolean status = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Date getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(Date boardingDate) {
        this.boardingDate = boardingDate;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }


    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
