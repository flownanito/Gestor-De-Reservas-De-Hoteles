package com.proyect.reservationmanager.model;

import jakarta.persistence.*; 
import jakarta.validation.constraints.*; 
import lombok.*; 

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Reservation") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 

public class Reservation {
  // PK: id_reservation
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationId;

  // Estado (VARCHAR(15), UNIQUE, NOT NULL)
  @Column(nullable = false, length = 15)
  @NotBlank(message = "Este campo es obligatorio")
  private String estado;

  // PRecio Total (VARCHAR(100), NOT NULL)
  @Column(nullable = false, length = 15)
  @NotBlank(message = "Este campo es obligatorio")
  private String precioTotal;

  // Numero Huesped (VARCHAR(100), NOT NULL)
  @Column(unique = true, nullable = false, length = 15)
  @NotBlank(message = "Este campo es obligatorio")
  private String numHuesped;

  // Fecha Reserva (DATE(dd/MM/yyyy), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false, length = 10)
  @NotBlank(message = "Este campo es obligatorio")
  private String fechaReserva;

  // Fecha Check In(DATE(dd/MM/yyyy), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false, length = 10)
  @NotBlank(message = "Este campo es obligatorio")
  private String fechaCheckIn;

  // Fecha Check Out (DATE(dd/MM/yyyy), NOT NULL)
  @DateTimeFormat
  @Column(nullable = false, length = 10)
  @NotBlank(message = "Este campo es obligatorio")
  private String fechaCheckOut;
}
