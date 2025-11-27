package com.proyect.reservationmanager.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.repository.ClientRepository;

import jakarta.validation.Valid;

@RestController // Marca la clase para manejar peticiones HTTP y devolver JSON/XML
@RequestMapping("/api/clients") // Define la URL base para este controlador
public class ClientController {
  @Autowired // Inyecta el repositorio para poder usar los métodos CRUD
  private ClientRepository clientRepository;

  // Endpoint: GET /api/clients
  @GetMapping
  public ResponseEntity<List<Client>>  getAllClients() {
    // Obtenemos la lista de la base de datos
    List<Client> clients = clientRepository.findAll();
    // Devolvemos la lista con el código 200 OK
    return new ResponseEntity<>(clients, HttpStatus.OK);
  }

  // Endpoint Get http://localhost:8080/api/clients/1
  @GetMapping("/{id}")
  public ResponseEntity<Client> getClientById(@PathVariable Long id) {
    // Optional maneja el caso de que el cliente no exista
    Optional<Client> client = clientRepository.findById(id);

    if (client.isPresent()) {
      return new ResponseEntity<>(client.get(), HttpStatus.OK); // Retorna 200 OK
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 NOT FOUND
    }
  }

  @GetMapping("/dni/{dni}")
  public ResponseEntity<Object> getClientByDni(@PathVariable String dni) {
    Optional<Client> client = clientRepository.findByDni(dni);

    if (client.isPresent()) {
      return new ResponseEntity<>(client.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Endpoint: POST /api/clients
  @PostMapping
  // @RequestBody mapea el JSON de la petición al objeto Client
  public ResponseEntity<Object> createClient(@Valid @RequestBody Client client) {
    if (clientRepository.findByDni(client.getDni()).isPresent()) {
      return new ResponseEntity<>("Error: El DNI ya existe", HttpStatus.CONFLICT);
    }

    if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
      return new ResponseEntity<>("Error: El email ya existe", HttpStatus.CONFLICT);
    }

    if (client.getRegistrationDate() == null) {
      client.setRegistrationDate(LocalDateTime.now());
    }

    Client savedClient = clientRepository.save(client);
    return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
  }

  // Endpoint PUT http://localhost:8080/api/clients/1
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
    // Buscamos si el cliente con el ID del Path existe
    return clientRepository.findById(id)
      .map(client -> {
        // Chequeo de DNI
        if (clientRepository.findByDniAndIdNot(clientDetails.getDni(), id).isPresent()) {
          return new ResponseEntity<Object>("Error 409: El DNI está en uso por otro cliente", HttpStatus.CONFLICT);
        }

        // Chequeo de email
        if (clientRepository.findByEmailAndIdNot(clientDetails.getEmail(), id).isPresent()) {
          return new ResponseEntity<Object>("Error 409: El email está en uso por otro cliente", HttpStatus.CONFLICT);
        }

        // Si todo es único actualizamos
        client.setDni(clientDetails.getDni());
        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setEmail(clientDetails.getEmail());
        client.setPhone(clientDetails.getPhone());

        // Guardamos y devolvemos 200 OK
        Client updateClient = clientRepository.save(client);
        return new ResponseEntity<Object>(updateClient, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Endpoint DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
    if (clientRepository.existsById(id)) {
      clientRepository.deleteById(id);
      // Si borramos con exito, devolvemos 204 No Content (el estandar para DELETE)
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      // si no existe para borrar devolvemos 404
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}

