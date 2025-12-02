package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.ReservationDetail;
import com.proyect.reservationmanager.repository.ReservationDetailRepository;

@RestController
@RequestMapping("/api/reservation-details")
public class ReservationDetailController {
  
  @Autowired
  private ReservationDetailRepository reservationDetailRepository;

  // Endpoint: GET /api/reservation-details
  @GetMapping
public ResponseEntity<List<ReservationDetail>> getAllReservations() {
    // Obtenemos la lista de reservas de la base de datos
    List<ReservationDetail> reservationsDetail = reservationDetailRepository.findAll();
    // retorna el codigo HTTP 200 "OK"
    return new ResponseEntity<>(reservationsDetail, HttpStatus.OK);
  }

  // Endpoint: GET /api/reservation-details/1
  @GetMapping("/{id}")
  public ResponseEntity<ReservationDetail> getReservationDetailById(@PathVariable Long id) {
    Optional<ReservationDetail> reservationDetail = reservationDetailRepository.findById(id);

    if (reservationDetail.isPresent()) {
      return new ResponseEntity<>(reservationDetail.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Endpoint: POST /api/reservation-details
  @PostMapping
  public ResponseEntity<ReservationDetail> createReservationDetail(@RequestBody ReservationDetail reservationDetail) {
    ReservationDetail savedReservationDetail = reservationDetailRepository.save(reservationDetail);
    return new ResponseEntity<>(savedReservationDetail, HttpStatus.CREATED);
  }

  // Endpoint: PUT /api/reservation-details/1
  @PutMapping("/{id}")
  public ResponseEntity<ReservationDetail> updateReservationDetail(
      @PathVariable Long id, 
      @RequestBody ReservationDetail reservationDetailDetails) {
    
    return reservationDetailRepository.findById(id)
      .map(reservationDetail -> {
        // Actualizamos los campos con los datos del JSON
        reservationDetail.setCheckInDate(reservationDetailDetails.getCheckInDate());
        reservationDetail.setCheckOutDate(reservationDetailDetails.getCheckOutDate());
        reservationDetail.setSubtotal(reservationDetailDetails.getSubtotal());
        reservationDetail.setPriceForNight(reservationDetailDetails.getPriceForNight());

        // Guardamos la entidad actualizada
        ReservationDetail updatedReservationDetail = reservationDetailRepository.save(reservationDetail);
        return new ResponseEntity<>(updatedReservationDetail, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Endpoint: DELETE /api/reservation-details/1
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservationDetail(@PathVariable Long id) {
    if (reservationDetailRepository.existsById(id)) {
      reservationDetailRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}