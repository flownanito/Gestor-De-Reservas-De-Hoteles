package com.proyect.reservationmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.model.Position;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Optional<Employee> findByEmail(String email);

  List<Employee> findByPositionPositionName(String positionName);
}
