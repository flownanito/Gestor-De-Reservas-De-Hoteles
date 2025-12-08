package com.proyect.reservationmanager.model;

public class RoomState {
    private Long id;
    private String stateName;

    public RoomState() {
    }

    public RoomState(Long id, String stateName) {
        this.id = id;
        this.stateName = stateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
