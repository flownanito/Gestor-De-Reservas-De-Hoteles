package com.proyect.reservationmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyect.reservationmanager.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
