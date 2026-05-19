package com.proyect.reservationmanager.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private String phone;
}
