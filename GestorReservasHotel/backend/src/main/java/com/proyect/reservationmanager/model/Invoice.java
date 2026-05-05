package com.proyect.reservationmanager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
  name = "invoices",
  uniqueConstraints = @UniqueConstraint(columnNames = "reservation_id")
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Factura única por reserva (constraint en BD arriba)
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "reservation_id", nullable = false, unique = true)
  @JsonIgnore
  private Reservation reservation;

  @Column(nullable = false)
  private LocalDateTime issuedAt;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal subtotal;

  @Column(nullable = false, precision = 5, scale = 4)
  private BigDecimal taxRate; // ej 0.10

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal taxAmount;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal total;
}
