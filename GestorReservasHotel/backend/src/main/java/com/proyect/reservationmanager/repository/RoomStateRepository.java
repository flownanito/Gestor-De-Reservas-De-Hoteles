package com.proyect.reservationmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.RoomState;

@Repository
public interface RoomStateRepository extends JpaRepository<RoomState, Long> {
  Optional<RoomState> findByStateName(String stateName);
}
