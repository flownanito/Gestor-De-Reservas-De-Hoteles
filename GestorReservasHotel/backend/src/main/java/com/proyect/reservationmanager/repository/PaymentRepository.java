package com.proyect.reservationmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.reservationmanager.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
  // Métodos CRUD provistos por JpaRepository
  List<Payment> findByStatus(String status);
}
