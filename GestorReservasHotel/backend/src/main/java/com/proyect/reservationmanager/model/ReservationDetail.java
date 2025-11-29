package com.proyect.reservationmanager.model;

import jakarta.persistence.*; 
import jakarta.validation.constraints.*; 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name = "ReservationDetail") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ReservationDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long reservationDetailId;

  @ManyToOne
  @JoinColumn(name = "reservationdetail", nullable = false)
  private Position reservation;

  @ManyToOne
  @JoinColumn(name = "reservationdetail", nullable = false)
  private Position room;

  // Fecha Check-In (DATE(10), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false, length = 10)
  @NotNull(message = "La fecha de check-in es obligatoria")
  private Date checkInDate;

  // Fecha Check-Out (DATE(10), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false)
  @NotNull(message = "La fecha de check-out es obligatoria")
  private Date checkOutDate;

  // Subtotal (FLOAT(10)), UNIQUE)
  @Column
  @NotNull(message = "El subtotal debe ser un valor válido")
  private Float subtotal;

  // Precio Por Noche (FLOAT(10)), UNIQUE)
  @Column
  @NotNull(message = "El precio por noche debe ser un valor válido")
  private Float priceForNight;
}
