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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.reservationmanager.model.Room;
import com.proyect.reservationmanager.model.RoomState;
import com.proyect.reservationmanager.model.RoomType;
import com.proyect.reservationmanager.repository.RoomRepository;
import com.proyect.reservationmanager.repository.RoomStateRepository;
import com.proyect.reservationmanager.repository.RoomTypeRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
  private final RoomRepository roomRepository;
  private final RoomStateRepository roomStateRepository;
  private final RoomTypeRepository roomTypeRepository;

  public RoomController(RoomRepository roomRepository,
      RoomStateRepository roomStateRepository,
      RoomTypeRepository roomTypeRepository) {
    this.roomRepository = roomRepository;
    this.roomStateRepository = roomStateRepository;
    this.roomTypeRepository = roomTypeRepository;
  }

  // ---------------------------------------------------------
  // GET ALL
  // ---------------------------------------------------------
  @GetMapping
  public ResponseEntity<List<Room>> getAllRooms() {
    List<Room> rooms = roomRepository.findAll();
    return new ResponseEntity<>(rooms, HttpStatus.OK);
  }

  // ---------------------------------------------------------
  // GET BY ID
  // ---------------------------------------------------------
  @GetMapping("/{id}")
  public ResponseEntity<Object> getRoomById(@PathVariable Long id) {
    Optional<Room> room = roomRepository.findById(id);

    if (room.isPresent()) {
      return new ResponseEntity<>(room.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------------------------------------------
  // GET BY ROOM NUMBER
  // ---------------------------------------------------------
  @GetMapping("/number/{roomNumber}")
  public ResponseEntity<Object> getRoomByNumber(@PathVariable String roomNumber) {
    Optional<Room> room = roomRepository.findByRoomNumber(roomNumber);

    if (room.isPresent()) {
      return new ResponseEntity<>(room.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Room with number " + roomNumber + " not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------------------------------------------
  // GET BY STATE NAME
  // ---------------------------------------------------------
  @GetMapping("/state/{stateName}")
  public ResponseEntity<List<Room>> getRoomsByState(@PathVariable String stateName) {
    List<Room> rooms = roomRepository.findByRoomStateStateName(stateName);
    return new ResponseEntity<>(rooms, HttpStatus.OK);
  }

  // ---------------------------------------------------------
  // GET BY TYPE NAME
  // ---------------------------------------------------------
  @GetMapping("/type/{typeName}")
  public ResponseEntity<List<Room>> getRoomsByType(@PathVariable String typeName) {
    List<Room> rooms = roomRepository.findByRoomTypeName(typeName);
    return new ResponseEntity<>(rooms, HttpStatus.OK);
  }

  // ---------------------------------------------------------
  // POST (CREATE)
  // ---------------------------------------------------------
  @PostMapping
  public ResponseEntity<Object> createRoom(@Valid @RequestBody Room room) {
    if (roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
      return new ResponseEntity<>( "La habitación ya existe", HttpStatus.CONFLICT);
    }

    RoomState roomState = room.getRoomState();
    if (roomState == null || roomState.getId() == null) {
      return new ResponseEntity<>("RoomState must not be null", HttpStatus.BAD_REQUEST);
    }

    Long stateId = roomState.getId();
    Optional<RoomState> stateOpt = roomStateRepository.findById(stateId);
    if (stateOpt.isEmpty()) {
      return new ResponseEntity<>("RoomState with ID " + stateId + " not found", HttpStatus.NOT_FOUND);
    }

    RoomType roomType = room.getRoomType();
    if (roomType == null || roomType.getId() == null) {
      return new ResponseEntity<>("RoomType must not be null", HttpStatus.BAD_REQUEST);
    }
    Long typeId = roomType.getId();
    Optional<RoomType> typeOpt = roomTypeRepository.findById(typeId);
    if (typeOpt.isEmpty()) {
      return new ResponseEntity<>("RoomType with ID " + typeId + " not found", HttpStatus.NOT_FOUND);
    }

    room.setRoomState(stateOpt.get());
    room.setRoomType(typeOpt.get());

    Room savedRoom = roomRepository.save(room);
    return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
  }

  // ---------------------------------------------------------
  // PUT (UPDATE)
  // ---------------------------------------------------------
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateRoom(@PathVariable Long id, @Valid @RequestBody Room roomDetails) {

    // Validamos RoomState
    Long stateId = roomDetails.getRoomState().getId();
    Optional<RoomState> stateOpt = roomStateRepository.findById(stateId);

    if (stateOpt.isEmpty()) {
      return new ResponseEntity<Object>("RoomState with ID " + stateId + " not found", HttpStatus.NOT_FOUND);
    }

    // Validamos RoomType
    Long typeId = roomDetails.getRoomType().getId();
    Optional<RoomType> typeOpt = roomTypeRepository.findById(typeId);

    if (typeOpt.isEmpty()) {
      return new ResponseEntity<Object>("RoomType with ID " + typeId + " not found", HttpStatus.NOT_FOUND);
    }

    return roomRepository.findById(id)
        .map(room -> {
          if (roomRepository.findByRoomNumberAndIdNot(roomDetails.getRoomNumber(), id).isPresent()) {
            return new ResponseEntity<Object>("Error 409", HttpStatus.CONFLICT);
          }

          room.setRoomNumber(roomDetails.getRoomNumber());
          room.setFloor(roomDetails.getFloor());
          room.setRoomState(stateOpt.get());
          room.setRoomType(typeOpt.get());

          Room updatedRoom = roomRepository.save(room);
          return new ResponseEntity<Object>(updatedRoom, HttpStatus.OK);
        })
        .orElse(new ResponseEntity<Object>("Room with ID " + id + " not found", HttpStatus.NOT_FOUND));
  }

  // ---------------------------------------------------------
  // DELETE
  // ---------------------------------------------------------
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
    if (roomRepository.existsById(id)) {
      roomRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
