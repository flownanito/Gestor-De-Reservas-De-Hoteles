package com.proyect.reservationmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roomFeature")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoomFeature {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "roomId", nullable = false)
  private Room room;

  @ManyToOne
  @JoinColumn(name = "featureId", nullable = false)
  private Feature feature;

}
