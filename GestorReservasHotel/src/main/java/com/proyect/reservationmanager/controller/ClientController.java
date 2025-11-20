package com.proyect.reservationmanager.controller;

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
  public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
    // Usa el metodo save()
    Client savedClient = clientRepository.save(client);

    // Retorna el cliente creado y un código de estado 201 (Created)
    return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
  }

  // Endpoint PUT http://localhost:8080/api/clients/1
  @PutMapping("/{id}")
  public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientDetails) {
    // Buscamos al cliente existente usando el ID
    return clientRepository.findById(id)
      .map(client -> {
        // Si existe (map) actualizamos los campos con los datos del JSON
        client.setDni(clientDetails.getDni());
        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setEmail(clientDetails.getEmail());
        client.setPhone(clientDetails.getPhone());
        // El ID y registrationDate no se suelen modificar en un PUT

        // Guardamos la entidad actualizada (Hibernate la mapea a un UPDATE)
        Client updateClient = clientRepository.save(client);

        // Devolvemos la respuesta 200 OK con el cliente actualizado
        return new ResponseEntity<>(updateClient, HttpStatus.OK);
      })
      // Si no existe (orElse), devolvemos 404 Not Found
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
