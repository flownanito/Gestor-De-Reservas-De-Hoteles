package com.proyect.reservationmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
