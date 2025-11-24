package com.proyect.reservationmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.reservationmanager.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findByRoomNumber(String roomNumber);

  List<Room> findByRoomStateStateName(String stateName);

  List<Room> findByRoomTypeName(String roomTypeName);
}
