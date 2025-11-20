package com.proyect.reservationmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.reservationmanager.model.Reservation;

@Repository // Indica que esta interfaz es un repositorio de Spring
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  // JpaRepository proporciona automáticamente los métodos CRUD básicos:
  // - save(Reservation reservation)
  // - findById(Long id)
  // - findAll()
  // - deleteById(Long id)
  // - existsById(Long id)
  // - count()
  // - etc.
  
  // Aquí puedes agregar métodos de consulta personalizados si los necesitas
  // Por ejemplo:
  
  // List<Reservation> findByCondition(String condition);
  // List<Reservation> findByCheckInDateBetween(Date startDate, Date endDate);
  // Optional<Reservation> findByReservationId(Long reservationId);
}