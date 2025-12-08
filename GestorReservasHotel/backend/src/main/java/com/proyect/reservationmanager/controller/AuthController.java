package com.proyect.reservationmanager.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.reservationmanager.dto.LoginRequest;
import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.repository.ClientRepository;
import com.proyect.reservationmanager.repository.EmployeeRepository;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

 @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Limpiamos espacios en blanco al recibir los datos
        String email = request.getEmail().trim();
        String password = request.getPassword().trim();

        System.out.println("🔍 LOGIN INTENTO -> Email: [" + email + "] Pass: [" + password + "]");

        // --- BÚSQUEDA DE EMPLEADO ---
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            // Limpiamos la pass de la BBDD también por si acaso
            String dbPass = employee.getPassword() != null ? employee.getPassword().trim() : "";

            if (dbPass.equals(password)) {
                return ResponseEntity.ok(employee);
            }
        }

        // --- BÚSQUEDA DE CLIENTE ---
        // Nota: findByEmailAndPassword busca exacto, así que mejor buscamos solo por email
        // y comprobamos la contraseña manualmente en Java
        Client client = clientRepository.findByEmail(email).orElse(null);

        if (client != null) {
             String dbPass = client.getPassword() != null ? client.getPassword().trim() : "";

             if (dbPass.equals(password)) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", client.getId());
                response.put("name", client.getFirstName());
                response.put("lastName", client.getLastName());
                response.put("email", client.getEmail());
                response.put("phone", client.getPhone());
                response.put("role", "CLIENT");
                return ResponseEntity.ok(response);
             }
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}
