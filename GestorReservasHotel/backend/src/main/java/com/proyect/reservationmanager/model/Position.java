package com.proyect.reservationmanager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "position")
@Data @NoArgsConstructor @AllArgsConstructor
public class Position {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Employee> employees;

  @Column(unique = true, nullable = false, length = 50)
  @NotBlank(message = "El nombre del puesto es obligatorio")
  private String positionName;
}
