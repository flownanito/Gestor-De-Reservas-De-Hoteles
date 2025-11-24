package com.proyect.reservationmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.reservationmanager.model.Feature;
import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomFeature;

@Repository
public interface RoomFeatureRepository extends JpaRepository<RoomFeature, Long> {
  List<RoomFeature> findByRoom(Room room);

  List<RoomFeature> findByFeature(Feature feature);
}
