package com.proyect.reservationmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    @NotNull(message = "La fecha de reserva es obligatoria")
    private LocalDateTime reservationDate;


    @Column(nullable = false)
    @NotNull(message = "La fecha de check-in es obligatoria")
    private LocalDate checkInDate;

    @Column(nullable = false)
    @NotNull(message = "La fecha de check-out es obligatoria")
    private LocalDate checkOutDate;

    // DATOS
    @Column(length = 50)
    @NotBlank(message = "El estado es obligatorio")
    private String condition;

    @Column(nullable = false)
    @NotNull(message = "El número de huéspedes es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 huésped")
    private Integer numberOfGuests;

    @Column(nullable = false)
    @NotNull(message = "El precio total es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double totalPrice;
}
