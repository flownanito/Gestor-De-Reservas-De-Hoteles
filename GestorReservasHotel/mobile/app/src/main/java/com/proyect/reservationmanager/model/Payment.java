package com.proyect.reservationmanager.model;

public class Payment {

    private Long paymentId;
    private Reservation reservation;
    private PaymentMethods paymentMethods;
    private String status;
    private Float amount;
    private String paymentDate;

    public Payment() {
    }

    public Payment(Long paymentId, Reservation reservation, PaymentMethods paymentMethods,
            String status, Float amount, String paymentDate) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.paymentMethods = paymentMethods;
        this.status = status;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public PaymentMethods getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(PaymentMethods paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
