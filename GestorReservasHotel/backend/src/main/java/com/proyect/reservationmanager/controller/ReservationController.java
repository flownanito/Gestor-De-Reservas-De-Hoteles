package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.Reservation;
import com.proyect.reservationmanager.repository.ReservationRepository;

@RestController // Marca la clase para manejar peticiones HTTP y devolver JSON/XML
@RequestMapping("/api/reservations") // Define la URL base para este controlador
public class ReservationController {
  
  @Autowired // Inyecta el repositorio para poder usar los métodos CRUD
  private ReservationRepository reservationRepository;

  // Endpoint: GET /api/reservations
  @GetMapping
public ResponseEntity<List<Reservation>> getAllReservations() {
    // Obtenemos la lista de reservas de la base de datos
    List<Reservation> reservations = reservationRepository.findAll();
    // retorna el codigo HTTP 200 "OK"
    return new ResponseEntity<>(reservations, HttpStatus.OK);
  }


  // Endpoint: GET http://localhost:8080/api/reservations/1
  @GetMapping("/{id}")
  public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
    // Optional maneja el caso de que la reserva no exista
    Optional<Reservation> reservation = reservationRepository.findById(id);

    if (reservation.isPresent()) {
      return new ResponseEntity<>(reservation.get(), HttpStatus.OK); // Retorna 200 OK
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 NOT FOUND
    }
  }

  // Endpoint: POST /api/reservations
  @PostMapping
  // @RequestBody mapea el JSON de la petición al objeto Reservation
  public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
    // Usa el método save()
    Reservation savedReservation = reservationRepository.save(reservation);

    // Retorna la reserva creada y un código de estado 201 (Created)
    return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
  }

  // Endpoint: PUT http://localhost:8080/api/reservations/1
  @PutMapping("/{id}")
  public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationDetails) {
    // Buscamos la reserva existente usando el ID
    return reservationRepository.findById(id)
      .map(reservation -> {
        // Si existe (map) actualizamos los campos con los datos del JSON
        reservation.setReservationDate(reservationDetails.getReservationDate());
        reservation.setCheckInDate(reservationDetails.getCheckInDate());
        reservation.setCheckOutDate(reservationDetails.getCheckOutDate());
        reservation.setCondition(reservationDetails.getCondition());
        reservation.setNumberOfGuests(reservationDetails.getNumberOfGuests());
        reservation.setTotalPrice(reservationDetails.getTotalPrice());
        // El reservationId no se modifica en un PUT

        // Guardamos la entidad actualizada (Hibernate la mapea a un UPDATE)
        Reservation updatedReservation = reservationRepository.save(reservation);

        // Devolvemos la respuesta 200 OK con la reserva actualizada
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
      })
      // Si no existe (orElse), devolvemos 404 Not Found
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Endpoint: DELETE http://localhost:8080/api/reservations/1
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
    if (reservationRepository.existsById(id)) {
      reservationRepository.deleteById(id);
      // Si borramos con éxito, devolvemos 204 No Content (el estándar para DELETE)
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      // Si no existe para borrar devolvemos 404
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}