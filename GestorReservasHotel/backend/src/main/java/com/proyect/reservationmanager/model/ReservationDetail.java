package com.proyect.reservationmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservationDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationDetailId;

  // Fecha Check-In (DATE(10), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false, length = 10)
  @NotBlank(message = "La fecha de check-in es obligatoria")
  private Date checkInDate;

  // Fecha Check-Out (DATE(10), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false, length = 10)
  @NotBlank(message = "La fecha de check-out es obligatoria")
  private Date CheckOutDate;

  // Subtotal (FLOAT(10)), UNIQUE)
  @Column(unique = true, length = 10)
  @NotBlank(message = "El subtotal debe ser un valor válido")
  private float subtotal;

  // Precio Por Noche (FLOAT(10)), UNIQUE)
  @Column(unique = true, length = 10)
  @NotBlank(message = "El precio por noche debe ser un valor válido")
  private float priceForNight;
}
