package com.proyect.reservationmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.sql.Date;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reservationId;

  @ManyToOne
  @JoinColumn(name = "id_empleado")
  private Employee employee;

  // Fecha Reserva (DATE(10), NOT NULL)
  @Column(nullable = false, length = 10)
  @NotBlank(message = "La fecha de reserva es obligatoria")
  private LocalDateTime reservationDate;

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

  // Estado (VARCHAR(50), UNIQUE)
  @Column(unique = true, length = 50)
  @NotBlank(message = "El estado debe ser un valor válido")
  private String condition;

  // Numero Huesped (VARCHAR(25), UNIQUE)
  @Column(unique = true, length = 25)
  @NotBlank(message = "El número de huésped debe ser un valor válido")
  private String numberOfGuests;

  // Precio Total (INTEGER(10)), UNIQUE)
  @Column(unique = true, length = 10)
  @NotBlank(message = "El precio total debe ser un valor válido")
  private Integer totalPrice;
}
