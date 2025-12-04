
package com.proyect.reservationmanager.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Data @NoArgsConstructor @AllArgsConstructor
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Reservation> reservations;

  @ManyToOne
  @JoinColumn(name = "position_id", nullable = false)
  private Position position;

  @NotBlank(message = "El nombre es obligatorio")
  @Column(nullable = false, length = 50)
  private String name;

  @NotBlank(message = "El apellido es obligatorio")
  @Column(nullable = false, length = 50)
  private String lastName;

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "El email debe ser valido")
  @Column(unique = true, nullable = false, length = 100)
  private String email;

  @Column(length = 20)
  private String phone;
}

