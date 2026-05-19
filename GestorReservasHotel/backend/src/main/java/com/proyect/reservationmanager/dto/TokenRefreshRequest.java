package com.proyect.reservationmanager.dto;

import lombok.Data;

@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
