package com.proyect.reservationmanager.model;

import com.google.gson.annotations.SerializedName;

/**
 * Modelo que representa un cargo/posición de un empleado.
 * Ejemplo: Recepcionista, Administrador, Gerente, etc.
 */
public class Position {

    @SerializedName("id")
    private Long id;

    @SerializedName("positionName")
    private String positionName;

    public Position() {}

    public Position(Long id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPositionName() { return positionName; }
    public void setPositionName(String positionName) { this.positionName = positionName; }
}
