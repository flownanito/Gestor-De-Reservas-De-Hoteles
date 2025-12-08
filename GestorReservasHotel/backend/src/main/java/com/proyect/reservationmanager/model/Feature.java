package com.proyect.reservationmanager.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "features")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Feature {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre de la caracteristic es obligatorio")
  @Column(unique = true, nullable = false, length = 50)
  private String featureName;

  @ManyToMany
  @JoinTable(name = "room_feature", // Nombre de la tabla de unión en la DB
      joinColumns = @JoinColumn(name = "feature_id"), // FK de esta entidad en la tabla de unión
      inverseJoinColumns = @JoinColumn(name = "room_id") // FK de la otra entidad (Room)
  )
  @JsonIgnore
  private Set<Room> rooms; // Usamos Set para la colección de Rooms
}
