package com.proyect.reservationmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "Payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentMethodsId;

  @Column(nullable = false, length = 100)
  @NotBlank(message = "El nombre del pago es obligatorio")
  private String paymentName;
}