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
@Table(name = "feature")
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

  @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<Feature> features;

}
