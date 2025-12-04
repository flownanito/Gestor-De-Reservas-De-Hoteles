package com.proyect.reservationmanager.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El numero es obligatorio")
  @Column(unique = true, nullable = false, length = 50)
  private String roomNumber;

  @NotNull(message = "El numero de planta es obligatorio")
  @Column(nullable = false)
  private Integer floor;

  @ManyToOne
  @JoinColumn(name = "room_state_id", nullable = false)
  private RoomState roomState;

  @ManyToOne
  @JoinColumn(name = "room_type_id", nullable = false)
  private RoomType roomType;

  @ManyToMany(mappedBy = "rooms")
  @JsonIgnore
  private Set<Feature> features;
}
