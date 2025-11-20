package com.proyect.reservationmanager.model;

import com.proyect.reservationmanager.model.RoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
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
  private int floor;

  @ManyToOne
  @JoinColumn(name = "roomStateId", nullable = false)
  private RoomState roomState;

  @ManyToOne
  @JoinColumn(name = "roomTypeId", nullable = false)
  private RoomType roomType;

}
