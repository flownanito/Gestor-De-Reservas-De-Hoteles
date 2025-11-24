package com.proyect.reservationmanager.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentId;

  @Column(nullable = false, length = 100)
  @NotBlank(message = "El estado del pago es obligatorio")
  private String status;

  @Column(nullable = false)
  @NotNull(message = "La cantidad es obligatoria")
  private Float amount;
  
  @Column(nullable = false)
  @NotNull(message = "La fecha de pago es obligatoria")
  private LocalDateTime paymentDate; 
}