package com.proyect.reservationmanager.model;

public class PaymentCard {
    public String number;
    public String holder;
    public String expiration;

    public PaymentCard(String number, String holder, String expiration) {
        this.number = number;
        this.holder = holder;
        this.expiration = expiration;
    }
}

