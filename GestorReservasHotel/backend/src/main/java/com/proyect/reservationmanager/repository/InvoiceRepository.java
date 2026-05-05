package com.proyect.reservationmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.reservationmanager.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsByReservation_Id(Long reservationId);

    Optional<Invoice> findByReservation_Id(Long reservationId);
}
