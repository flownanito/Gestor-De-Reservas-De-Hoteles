package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.Position;
import com.proyect.reservationmanager.repository.PositionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
  @Autowired
  private PositionRepository positionRepository;

  @PostMapping
  public ResponseEntity<Position> createPosition(@Valid @RequestBody Position position) {
    Position savedPosition = positionRepository.save(position);
    return new ResponseEntity<>(savedPosition, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Position>> getAllPositions() {
    List<Position> positions = positionRepository.findAll();
    return new ResponseEntity<>(positions, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
    Optional<Position> position = positionRepository.findById(id);

    if (position.isPresent()) {
      return new ResponseEntity<>(position.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Position> updatePosition(@PathVariable Long id, @Valid @RequestBody Position positionDetails) {
    return positionRepository.findById(id)
      .map(position -> {
        position.setPositionName(positionDetails.getPositionName());
        Position updatePosition = positionRepository.save(position);
        return new ResponseEntity<>(updatePosition, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePosition(@PathVariable Long id) {
    if (positionRepository.existsById(id)) {
      positionRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
