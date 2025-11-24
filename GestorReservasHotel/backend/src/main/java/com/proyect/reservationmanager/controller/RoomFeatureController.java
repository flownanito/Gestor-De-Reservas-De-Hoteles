package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.reservationmanager.model.Feature;
import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomFeature;
import com.proyect.reservationmanager.repository.FeatureRepository;
import com.proyect.reservationmanager.repository.RoomFeatureRepository;
import com.proyect.reservationmanager.repository.RoomRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-features")
public class RoomFeatureController {
  private final RoomFeatureRepository roomFeatureRepository;
  private final RoomRepository roomRepository;
  private final FeatureRepository featureRepository;

  public RoomFeatureController(RoomFeatureRepository roomFeatureRepository,
      RoomRepository roomRepository,
      FeatureRepository featureRepository) {
    this.roomFeatureRepository = roomFeatureRepository;
    this.roomRepository = roomRepository;
    this.featureRepository = featureRepository;
  }

  // ---------------------- GET ALL ----------------------
  @GetMapping
  public ResponseEntity<List<RoomFeature>> getAllRoomFeatures() {
    List<RoomFeature> list = roomFeatureRepository.findAll();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  // ---------------------- GET BY ID ----------------------
  @GetMapping("/{id}")
  public ResponseEntity<Object> getRoomFeatureById(@PathVariable Long id) {
    Optional<RoomFeature> rf = roomFeatureRepository.findById(id);

    if (rf.isPresent()) {
      return new ResponseEntity<>(rf.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("RoomFeature not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- GET BY ROOM ----------------------
  @GetMapping("/room/{roomId}")
  public ResponseEntity<Object> getRoomFeaturesByRoom(@PathVariable Long roomId) {
    Optional<Room> roomOpt = roomRepository.findById(roomId);
    if (roomOpt.isEmpty()) {
      return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
    }
    List<RoomFeature> list = roomFeatureRepository.findByRoom(roomOpt.get());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  // ---------------------- GET BY FEATURE ----------------------
  @GetMapping("/feature/{featureId}")
  public ResponseEntity<Object> getRoomFeaturesByFeature(@PathVariable Long featureId) {
    Optional<Feature> featureOpt = featureRepository.findById(featureId);
    if (featureOpt.isEmpty()) {
      return new ResponseEntity<>("Feature not found", HttpStatus.NOT_FOUND);
    }
    List<RoomFeature> list = roomFeatureRepository.findByFeature(featureOpt.get());
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  // ---------------------- CREATE ----------------------
  @PostMapping
  public ResponseEntity<Object> createRoomFeature(@Valid @RequestBody RoomFeature roomFeature) {

    Optional<Room> roomOpt = roomRepository.findById(roomFeature.getRoom().getId());
    if (roomOpt.isEmpty()) {
      return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
    }

    Optional<Feature> featureOpt = featureRepository.findById(roomFeature.getFeature().getId());
    if (featureOpt.isEmpty()) {
      return new ResponseEntity<>("Feature not found", HttpStatus.NOT_FOUND);
    }

    roomFeature.setRoom(roomOpt.get());
    roomFeature.setFeature(featureOpt.get());

    RoomFeature saved = roomFeatureRepository.save(roomFeature);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  // ---------------------- DELETE ----------------------
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoomFeature(@PathVariable Long id) {
    if (roomFeatureRepository.existsById(id)) {
      roomFeatureRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
