package com.proyect.reservationmanager.model;

public class Room {
    private String number;
    private String type;
    private double pricePerNight;
    private String description;
    private String status; // Available, Occupied, Maintenance

    public Room(String number, String type, double pricePerNight, String description, String status) {
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.description = description;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
