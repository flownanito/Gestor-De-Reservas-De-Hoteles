package com.proyect.reservationmanager.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Reservation implements Serializable {

    @SerializedName("reservationId")
    private Long reservationId;

    @SerializedName("reservationDate")
    private String reservationDate;

    @SerializedName("checkInDate")
    private String checkInDate;

    @SerializedName("checkOutDate")
    private String checkOutDate;

    @SerializedName("condition")
    private String condition;

    @SerializedName("numberOfGuests")
    private String numberOfGuests;

    @SerializedName("totalPrice")
    private Integer totalPrice;

    public Reservation() {
    }

    public Reservation(Long reservationId, String reservationDate, String checkInDate,
                       String checkOutDate, String condition, String numberOfGuests,
                       Integer totalPrice) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.condition = condition;
        this.numberOfGuests = numberOfGuests;
        this.totalPrice = totalPrice;
    }

    // Getters y Setters

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Método toString para depurar
    @Override
    public String toString() {
        return "Reserva #" + reservationId + " (" + checkInDate + " - " + checkOutDate + ")";
    }
}