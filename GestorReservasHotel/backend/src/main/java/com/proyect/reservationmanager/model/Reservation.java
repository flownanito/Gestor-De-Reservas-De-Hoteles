package com.proyect.reservationmanager.model;

import jakarta.persistence.*; 
import jakarta.validation.constraints.*; 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "Reservation") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Reservation {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long reservationId;

  // Relations: reservation can have many reservation details
  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Reservation> payment;

  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Reservation> reservationDetails;

  @ManyToOne
  @JoinColumn(name = "reservation", nullable = false)
  private Position Client;

  @ManyToOne
  @JoinColumn(name = "reservation", nullable = false)
  private Position employee;

  // Fecha Reserva (DATE(10), NOT NULL)
  @Column(nullable = false)
  @NotNull(message = "La fecha de reserva es obligatoria")
  private LocalDateTime reservationDate;

  // Fecha Check-In (DATE(10), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false)
  @NotNull(message = "La fecha de check-in es obligatoria")
  private Date checkInDate;

  // Fecha Check-Out (DATE(10), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false)
  @NotNull(message = "La fecha de check-out es obligatoria")
  private Date checkOutDate;

  // Estado (VARCHAR(50), UNIQUE)
  @Column(length = 50)
  @NotBlank(message = "El estado debe ser un valor válido")
  private String condition;

  // Numero Huesped (VARCHAR(25), UNIQUE)
  @Column(length = 25)
  @NotBlank(message = "El número de huésped debe ser un valor válido")
  private String numberOfGuests;

  // Precio Total (INTEGER(10)), UNIQUE)
  @Column
  @NotNull(message = "El precio total debe ser un valor válido")
  private Integer totalPrice;
}