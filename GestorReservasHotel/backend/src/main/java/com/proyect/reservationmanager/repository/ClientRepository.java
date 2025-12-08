
package com.proyect.reservationmanager.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.Client;

@Repository // Indica que es un componente de la capa de persistencia
// Hereda todos los métodos CRUD básicos (save, findById, findAll, delete)
// <Entidad a manejar, Tipo de dato de la PK de esa entidad>
public interface ClientRepository extends JpaRepository<Client, Long> {
  // Spring Data JPA permite definir métodos personalizados y Spring los implementa
  // Por ejemplo, para buscar un cliente por su DNI, simplemente defines la firma
  Optional<Client> findByDni(String dni);
  Optional<Client> findByEmail(String email);
  Optional<Client> findByDniAndIdNot(String dni, Long id);
  Optional<Client> findByEmailAndIdNot(String email, Long id);
  Client findByEmailAndPassword(String email, String password);
}


