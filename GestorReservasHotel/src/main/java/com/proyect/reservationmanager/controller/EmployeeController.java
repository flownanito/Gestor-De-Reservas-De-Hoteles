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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.repository.EmployeeRepository;
import com.proyect.reservationmanager.repository.PositionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;

  public EmployeeController(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
    this.employeeRepository = employeeRepository;
    this.positionRepository= positionRepository;
  }

  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
    Optional<Employee> employee = employeeRepository.findById(id);

    if (employee.isPresent()) {
      return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee employee) {
    Long positionId = employee.getPosition().getId();

    return positionRepository.findById(positionId)
      .map(existingPosition -> {
        employee.setPosition(existingPosition);
        Employee savedEmployee = employeeRepository.save(employee);
        return new ResponseEntity<Object>(savedEmployee, HttpStatus.CREATED);
      })
      .orElse(
        new ResponseEntity<Object>("El puesto con el ID: " + positionId + " no se ha podido encontrar.", HttpStatus.NOT_FOUND));
  }

  @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        Long newPositionId = employeeDetails.getPosition().getId();

        // 1. Buscamos si el nuevo puesto existe
        return positionRepository.findById(newPositionId)
            .map(existingPosition -> {
                // 2. Buscamos si el empleado existe
                return employeeRepository.findById(id)
                    .map(employee -> {
                        // 3. Actualizamos datos
                        employee.setName(employeeDetails.getName());
                        employee.setLastName(employeeDetails.getLastName());
                        employee.setEmail(employeeDetails.getEmail());
                        employee.setPhone(employeeDetails.getPhone());
                        employee.setPosition(existingPosition); // Asignamos el puesto encontrado

                        Employee updatedEmployee = employeeRepository.save(employee);
                        return new ResponseEntity<Object>(updatedEmployee, HttpStatus.OK);
                    })
                    .orElse(new ResponseEntity<Object>("Employee with ID " + id + " not found.", HttpStatus.NOT_FOUND));
            })
            .orElse(new ResponseEntity<Object>("Position with ID " + newPositionId + " not found.", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
      if (employeeRepository.existsById(id)) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
}
