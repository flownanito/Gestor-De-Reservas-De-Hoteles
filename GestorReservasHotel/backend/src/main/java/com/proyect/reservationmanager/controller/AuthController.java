package com.proyect.reservationmanager.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyect.reservationmanager.dto.LoginRequest;
import com.proyect.reservationmanager.dto.RegisterRequest;
import com.proyect.reservationmanager.dto.JwtResponse;
import com.proyect.reservationmanager.dto.TokenRefreshRequest;
import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.repository.ClientRepository;
import com.proyect.reservationmanager.repository.EmployeeRepository;
import com.proyect.reservationmanager.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String email = request.getEmail().trim();
        String password = request.getPassword().trim();

        System.out.println("🔍 LOGIN INTENTO -> Email: [" + email + "] Pass: [" + password + "]");

        // --- BÚSQUEDA DE EMPLEADO ---
        Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            String dbPass = employee.getPassword() != null ? employee.getPassword().trim() : "";

            if (dbPass.equals(password)) {
                String role = employee.getPosition() != null ? employee.getPosition().getPositionName().toUpperCase() : "EMPLOYEE";
                String accessToken = jwtService.generateAccessToken(email, role);
                String refreshToken = jwtService.generateRefreshToken(email);

                JwtResponse response = JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .id(employee.getId())
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .phone(employee.getPhone())
                        .role(role)
                        .build();

                return ResponseEntity.ok(response);
            }
        }

        // --- BÚSQUEDA DE CLIENTE ---
        Client client = clientRepository.findByEmail(email).orElse(null);
        if (client != null) {
             String dbPass = client.getPassword() != null ? client.getPassword().trim() : "";

             if (dbPass.equals(password)) {
                 String accessToken = jwtService.generateAccessToken(email, "CLIENT");
                 String refreshToken = jwtService.generateRefreshToken(email);

                 JwtResponse response = JwtResponse.builder()
                         .accessToken(accessToken)
                         .refreshToken(refreshToken)
                         .id(client.getId())
                         .name(client.getFirstName())
                         .lastName(client.getLastName())
                         .email(client.getEmail())
                         .phone(client.getPhone())
                         .role("CLIENT")
                         .build();

                 return ResponseEntity.ok(response);
             }
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (clientRepository.findByDni(request.getDni()).isPresent()) {
            return new ResponseEntity<>("Error: El DNI ya existe", HttpStatus.CONFLICT);
        }

        if (clientRepository.findByEmail(request.getEmail()).isPresent()) {
            return new ResponseEntity<>("Error: El email ya existe", HttpStatus.CONFLICT);
        }

        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setDni(request.getDni());
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword()); 
        client.setPhone(request.getPhone());
        client.setRegistrationDate(LocalDateTime.now());

        Client savedClient = clientRepository.save(client);

        String accessToken = jwtService.generateAccessToken(savedClient.getEmail(), "CLIENT");
        String refreshToken = jwtService.generateRefreshToken(savedClient.getEmail());

        JwtResponse response = JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .id(savedClient.getId())
                .name(savedClient.getFirstName())
                .lastName(savedClient.getLastName())
                .email(savedClient.getEmail())
                .phone(savedClient.getPhone())
                .role("CLIENT")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        if (requestRefreshToken != null && jwtService.validateToken(requestRefreshToken)) {
            String email = jwtService.extractEmail(requestRefreshToken);
            
            // Verificamos si es empleado
            Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
            if (employeeOpt.isPresent()) {
                Employee employee = employeeOpt.get();
                String role = employee.getPosition() != null ? employee.getPosition().getPositionName().toUpperCase() : "EMPLOYEE";
                String newAccessToken = jwtService.generateAccessToken(email, role);
                
                JwtResponse response = JwtResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(requestRefreshToken) 
                        .id(employee.getId())
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .phone(employee.getPhone())
                        .role(role)
                        .build();
                return ResponseEntity.ok(response);
            }

            // Verificamos si es cliente
            Client client = clientRepository.findByEmail(email).orElse(null);
            if (client != null) {
                String newAccessToken = jwtService.generateAccessToken(email, "CLIENT");
                
                JwtResponse response = JwtResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(requestRefreshToken)
                        .id(client.getId())
                        .name(client.getFirstName())
                        .lastName(client.getLastName())
                        .email(client.getEmail())
                        .phone(client.getPhone())
                        .role("CLIENT")
                        .build();
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Refresh Token inválido o expirado");
    }
}
