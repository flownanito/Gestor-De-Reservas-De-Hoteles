
package com.proyect.reservationmanager.model;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre es obligatorio")
  @Column(unique = true, nullable = false, length = 50)
  private String name;

  @NotNull(message = "El precio base es obligatorio")
  @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
  @Column(nullable = false)
  private BigDecimal basePrice;

  @Column(length = 255)
  private String description;

  @NotNull(message = "El numero de camas es obligatorio")
  @Min(value = 1, message = "Debe haber al menos una cama")
  @Column(nullable = false)
  private Integer numBeds;

  @NotNull(message = "La capacidad es obligatoria")
  @Min(value = 1, message = "Debe haber capacidad para uno como minimo")
  @Column(nullable = false)
  private Integer capacity;

  @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private Set<Room> rooms;
}

