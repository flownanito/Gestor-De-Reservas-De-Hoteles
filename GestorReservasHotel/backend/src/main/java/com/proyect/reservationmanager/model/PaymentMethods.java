package com.proyect.reservationmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Entidad para métodos de pago (PaymentMethods)
 * Atributos solicitados:
 * - idPaymentMethods: identificador autoincremental
 * - paymentName: nombre del método de pago
 */
@Entity
@Table(name = "payment_methods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethods {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_payment_methods")
  private Long idPaymentMethods;

  @Column(name = "payment_name", nullable = false, length = 100)
  @NotBlank(message = "El nombre del método de pago es obligatorio")
  private String paymentName;
}
