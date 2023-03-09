package com.may.MAYAirlines.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.may.MAYAirlines.entity.Reservation;

import java.util.Date;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReservationDTO {
    private int id;
    private int customerId;
    private int flightId;
    private String seatNo;
    private Date createDate;
    private Date updateDate;
    private boolean status;

    public ReservationDTO(int id, int customerId, int flightId, String seatNo, Date createDate, Date updateDate, boolean status) {
        this.id=id;
        this.customerId=customerId;
        this.flightId=flightId;
        this.seatNo=seatNo;
        this.createDate=createDate;
        this.updateDate=updateDate;
        this.status=status;
    }

//    public ReservationDTO(Reservation reservation){
//        this.id=reservation.getId();
//        this.customerId=reservation.getCustomer().getId();
//        this.flightId=reservation.getFlight().getId();
//        this.seatNo=reservation.getSeatNo();
//        this.createDate=reservation.getCreateDate();
//        this.updateDate=reservation.getUpdateDate();
//        this.status= reservation.isStatus();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public int getFlightId() {
//        return flightId;
//    }
//
//    public void setFlightId(int flightId) {
//        this.flightId = flightId;
//    }
//
//    public String getSeatNo() {
//        return seatNo;
//    }
//
//    public void setSeatNo(String seatNo) {
//        this.seatNo = seatNo;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
}
