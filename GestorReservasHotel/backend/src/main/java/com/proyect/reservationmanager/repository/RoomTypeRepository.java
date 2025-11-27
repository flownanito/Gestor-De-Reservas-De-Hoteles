package com.proyect.reservationmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
  Optional<RoomType> findByName(String name);
  Optional<RoomType> findByNameAndIdNot(String name, Long id);
}
