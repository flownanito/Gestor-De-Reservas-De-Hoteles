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

import com.proyect.reservationmanager.model.RoomState;
import com.proyect.reservationmanager.repository.RoomStateRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-states")
public class RoomStateController {
  private final RoomStateRepository roomStateRepository;

  public RoomStateController(RoomStateRepository roomStateRepository) {
    this.roomStateRepository = roomStateRepository;
  }

  // ---------------------- GET ALL ----------------------
  @GetMapping
  public ResponseEntity<List<RoomState>> getAllRoomStates() {
    List<RoomState> states = roomStateRepository.findAll();
    return new ResponseEntity<>(states, HttpStatus.OK);
  }

  // ---------------------- GET BY ID ----------------------
  @GetMapping("/{id}")
  public ResponseEntity<Object> getRoomStateById(@PathVariable Long id) {
    Optional<RoomState> state = roomStateRepository.findById(id);
    if (state.isPresent()) {
      return new ResponseEntity<>(state.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("RoomState not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- CREATE ----------------------
  @PostMapping
  public ResponseEntity<Object> createRoomState(@Valid @RequestBody RoomState roomState) {
    RoomState savedState = roomStateRepository.save(roomState);
    return new ResponseEntity<>(savedState, HttpStatus.CREATED);
  }

  // ---------------------- UPDATE ----------------------
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateRoomState(@PathVariable Long id, @Valid @RequestBody RoomState roomStateDetails) {
    Optional<RoomState> stateOpt = roomStateRepository.findById(id);

    if (stateOpt.isPresent()) {
      RoomState state = stateOpt.get();
      state.setStateName(roomStateDetails.getStateName());
      RoomState updatedState = roomStateRepository.save(state);
      return new ResponseEntity<>(updatedState, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("RoomState with ID " + id + " not found", HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- DELETE ----------------------
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoomState(@PathVariable Long id) {
    if (roomStateRepository.existsById(id)) {
      roomStateRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // ---------------------- GET BY NAME ----------------------
  @GetMapping("/name/{stateName}")
  public ResponseEntity<Object> getRoomStateByName(@PathVariable String stateName) {
    Optional<RoomState> states = roomStateRepository.findByStateName(stateName);

    if (states.isPresent()) {
      return new ResponseEntity<>(states.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
