package com.proyect.reservationmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


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

  @OneToMany(mappedBy = "paymentMethods", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Payment> payments;


  @Column(name = "payment_name", nullable = false, length = 100)
  @NotBlank(message = "El nombre del método de pago es obligatorio")
  private String paymentName;
}
