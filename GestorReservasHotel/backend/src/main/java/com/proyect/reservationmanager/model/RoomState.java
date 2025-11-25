package com.proyect.reservationmanager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_state")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomState {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre de estado es obligatorio")
  @Column(nullable = false, length = 50, unique = true)
  private String stateName;

  @OneToMany(mappedBy = "roomState", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Room> rooms;
}
