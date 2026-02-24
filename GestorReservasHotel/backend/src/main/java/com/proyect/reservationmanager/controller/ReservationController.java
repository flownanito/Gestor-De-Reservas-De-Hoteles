package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.model.Reservation;
import com.proyect.reservationmanager.model.RoomType;
import com.proyect.reservationmanager.repository.ClientRepository;
import com.proyect.reservationmanager.repository.EmployeeRepository;
import com.proyect.reservationmanager.repository.ReservationRepository;
import com.proyect.reservationmanager.repository.RoomTypeRepository;

@RestController // Marca la clase para manejar peticiones HTTP y devolver JSON/XML
@RequestMapping("/api/reservations") // Define la URL base para este controlador
public class ReservationController {

  @Autowired // Inyecta el repositorio para poder usar los métodos CRUD
  private ReservationRepository reservationRepository;

  @Autowired
  private RoomTypeRepository roomTypeRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

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

  @GetMapping("/client/{clientId}")
  public ResponseEntity<List<Reservation>> getReservationsByClient(@PathVariable Long clientId) {
    List<Reservation> reservations = reservationRepository.findByClient_Id(clientId);
    return new ResponseEntity<>(reservations, HttpStatus.OK);
  }

  // Endpoint: POST /api/reservations
  @Transactional
  @PostMapping
  // @RequestBody mapea el JSON de la petición al objeto Reservation
  public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {

    // Validar client.id
    if (reservation.getClient() == null || reservation.getClient().getId() == null) {
      return new ResponseEntity<>("client.id es obligatorio", HttpStatus.BAD_REQUEST);
    }

    // Validar roomType.id
    if (reservation.getRoomType() == null || reservation.getRoomType().getId() == null) {
      return new ResponseEntity<>("roomType.id es obligatorio", HttpStatus.BAD_REQUEST);
    }

    Long clientId = reservation.getClient().getId();
    Long roomTypeId = reservation.getRoomType().getId();

    // Cargar entidades reales
    Client client = clientRepository.findById(clientId)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    RoomType roomType = roomTypeRepository.findByIdForUpdate(roomTypeId)
        .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));

    // (Opcional) employee: si llega null, ok; si llega id, lo cargas
    Employee employee = null;
    if (reservation.getEmployee() != null && reservation.getEmployee().getId() != null) {
      employee = employeeRepository.findById(reservation.getEmployee().getId())
          .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    // Validar número de huéspedes (capacidad)
    Integer guests = reservation.getNumberOfGuests();
    if (guests == null)
      guests = 1;

    if (guests > roomType.getCapacity()) {
      return new ResponseEntity<>(
          "Esta habitación permite máximo " + roomType.getCapacity() + " huésped(es).",
          HttpStatus.BAD_REQUEST);
    }

    // Validar fechas de forma correcta
    if (reservation.getCheckOutDate().isBefore(reservation.getCheckInDate())) {
      return new ResponseEntity<>("La fecha de check-out no puede ser anterior al check-in",
          HttpStatus.BAD_REQUEST);
    }

    // Validar disponibilidad (stock)
    if (roomType.getAvailableRooms() == null || roomType.getAvailableRooms() <= 0) {
      return new ResponseEntity<>("No hay habitaciones disponibles", HttpStatus.CONFLICT);
    }

    // Actualizar disponibilidad
    roomType.setAvailableRooms(roomType.getAvailableRooms() - 1);
    // No es obligatorio llamar a save(roomType) aquí si roomType está "managed"
    // pero lo puedes dejar si quieres:
    // roomTypeRepository.save(roomType);

    // Asignar entidades reales a la reserva
    reservation.setClient(client);
    reservation.setRoomType(roomType);
    reservation.setEmployee(employee);

    Reservation savedReservation = reservationRepository.save(reservation);

    return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
  }

  // Endpoint: PUT http://localhost:8080/api/reservations/1
  @PutMapping("/{id}")
  public ResponseEntity<Reservation> updateReservation(@PathVariable Long id,
      @RequestBody Reservation reservationDetails) {
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
  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {

    Optional<Reservation> reservationOpt = reservationRepository.findById(id);

    if (reservationOpt.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    Reservation reservation = reservationOpt.get();

    RoomType roomType = roomTypeRepository.findByIdForUpdate(reservation.getRoomType().getId())
        .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));

    int avail = roomType.getAvailableRooms() == null ? 0 : roomType.getAvailableRooms();
    int total = roomType.getTotalRooms() == null ? 0 : roomType.getTotalRooms();

    // Devolver al inventario sin pasarse del total
    roomType.setAvailableRooms(Math.min(avail + 1, total));

    roomTypeRepository.save(roomType);

    // Borrar la reserva
    reservationRepository.delete(reservation);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
