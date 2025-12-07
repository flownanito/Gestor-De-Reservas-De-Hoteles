package com.proyect.reservationmanager.model;

import java.util.List;
public class Room {
    private Long id;
    private String roomNumber;
    private Integer floor;
    private RoomState roomState;
    private RoomType roomType;
    private List<Feature> features;

    public Room() {
    }

    public Room(Long id, String roomNumber, Integer floor, RoomState roomState, RoomType roomType, List<Feature> features) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomState = roomState;
        this.roomType = roomType;
        this.features = features;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public RoomState getRoomState() {
        return roomState;
    }

    public void setRoomState(RoomState roomState) {
        this.roomState = roomState;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}

