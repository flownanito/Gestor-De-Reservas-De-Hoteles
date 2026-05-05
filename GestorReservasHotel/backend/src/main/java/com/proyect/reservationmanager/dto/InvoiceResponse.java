package com.proyect.reservationmanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceResponse {
    // Invoice
    private Long invoiceId;
    private LocalDateTime issuedAt;
    private BigDecimal subtotal;
    private BigDecimal taxRate;
    private BigDecimal taxAmount;
    private BigDecimal total;

    // Reservation
    private Long reservationId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long nights;

    // Client
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientDni;
    private String clientEmail;

    // RoomType
    private Long roomTypeId;
    private String roomTypeName;
    private BigDecimal pricePerNight;
}
