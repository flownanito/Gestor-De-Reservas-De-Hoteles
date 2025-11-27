package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.reservationmanager.model.RoomType;
import com.proyect.reservationmanager.repository.RoomTypeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {
  private final RoomTypeRepository roomTypeRepository;

  public RoomTypeController(RoomTypeRepository roomTypeRepository) {
    this.roomTypeRepository = roomTypeRepository;
  }

  @GetMapping
  public ResponseEntity<List<RoomType>> getAllRoomTypes() {
    List<RoomType> roomTypes = roomTypeRepository.findAll();
    return new ResponseEntity<>(roomTypes, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoomType> getAllRoomTypesById(@PathVariable Long id) {
    Optional<RoomType> roomType = roomTypeRepository.findById(id);

    if (roomType.isPresent()) {
      return new ResponseEntity<>(roomType.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Object> createRoomType(@Valid @RequestBody RoomType roomType) {
    if (roomTypeRepository.findByName(roomType.getName()).isPresent()) {
      return new ResponseEntity<Object>("Error, el nombre ya existe", HttpStatus.CONFLICT);
    }

    RoomType savedRoomType = roomTypeRepository.save(roomType);
    return new ResponseEntity<Object>(savedRoomType, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateRoomType(@PathVariable Long id, @Valid @RequestBody RoomType roomTypeDetails) {
    return roomTypeRepository.findById(id)
      .map(roomType -> {
        if (roomTypeRepository.findByNameAndIdNot(roomTypeDetails.getName(), id).isPresent()) {
          return new ResponseEntity<Object>("Error el nombre ya existe", HttpStatus.CONFLICT);
        }

        roomType.setName(roomTypeDetails.getName());
        roomType.setBasePrice(roomTypeDetails.getBasePrice());
        roomType.setDescription(roomTypeDetails.getDescription());
        roomType.setNumBeds(roomTypeDetails.getNumBeds());
        roomType.setCapacity(roomTypeDetails.getCapacity());

        RoomType updateRoomType = roomTypeRepository.save(roomType);
        return new ResponseEntity<Object>(updateRoomType, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<Object>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoomType(@PathVariable Long id) {
    if (roomTypeRepository.existsById(id)) {
      roomTypeRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
