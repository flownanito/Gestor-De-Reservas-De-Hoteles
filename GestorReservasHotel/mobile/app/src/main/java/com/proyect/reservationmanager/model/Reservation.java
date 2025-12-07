package com.proyect.reservationmanager.model;

public class Reservation {
    private Long reservationId;

    // Backend: LocalDateTime → Android: String (ISO 8601)
    private String reservationDate;

    // Backend: java.sql.Date → Android: String ("YYYY-MM-DD")
    private String checkInDate;

    // Backend: java.sql.Date → Android: String
    private String checkOutDate;

    private String condition;
    private String numberOfGuests;
    private Integer totalPrice;

    public Reservation() {}

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
}
