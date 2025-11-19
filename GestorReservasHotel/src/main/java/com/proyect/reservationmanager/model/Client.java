package com.proyect.reservationmanager.model;

import jakarta.persistence.*; // Paquete para JPA (Hibernate)
import jakarta.validation.constraints.*; // Para validaciones
import lombok.*; // Para simplificar el código Java

import java.time.LocalDateTime;

@Entity // Indica que esta clase es una tabla de base de datos
@Table(name = "Clients") // Especifica el nombre real de la tabla en la BD
@Data // Lombok genera los metodos get/set/toString automaticamente
@NoArgsConstructor // Lombok Constructor sin argumentos (obligatorio para JPA)
@AllArgsConstructor // Lombok Constructor con todos los argumentos
public class Client {
  // PK: id_cliente
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL usa IDENTIFY para auto-incremento
  private Long id;

  // dni (VARCHAR(15), UNIQUE, NOT NULL)
  @Column(unique = true, nullable = false, length = 15)
  @NotBlank(message = "DNI is mandatory")
  private String dni;

  // nombre (VARCHAR(100), NOT NULL)
  @Column(nullable = false, length = 100)
  @NotBlank(message = "El nombre es obligatorio")
  private String firstName;

  // apellido (VARCHAR(100), NOT NULL)
  @Column(nullable = false, length = 100)
  @NotBlank(message = "El apellido es obligatorio")
  private String lastName;

  // email (VARCHAR(100), UNIQUE)
  @Column(unique = true, length = 100)
  @Email(message = "El email debe ser valido")
  private String email;

  // telefono (VARCHAR(20))
  @Column(length = 20)
  private String phone;

  // fecha_registro (TIMESTAMP, NOT NULL)
  @Column(nullable = false)
  @NotNull
  private LocalDateTime registrationDate;
}
