package com.proyect.reservationmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.reservationmanager.model.ReservationDetail;

@Repository
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {
  // JpaRepository ya proporciona métodos CRUD básicos:
  // - findAll()
  // - findById(Long id)
  // - save(ReservationDetail entity)
  // - deleteById(Long id)
  // - existsById(Long id)
  
  // Aquí puedes agregar métodos personalizados si los necesitas, por ejemplo:
  // List<ReservationDetail> findBySubtotalGreaterThan(float subtotal);
}