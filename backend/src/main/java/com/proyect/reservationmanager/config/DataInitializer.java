package com.proyect.reservationmanager.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.model.Position;
import com.proyect.reservationmanager.repository.ClientRepository;
import com.proyect.reservationmanager.repository.EmployeeRepository;
import com.proyect.reservationmanager.repository.PositionRepository;

@Component
public class DataInitializer implements CommandLineRunner {
  private final ClientRepository clientRepository;
  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;

  public DataInitializer(ClientRepository clientRepository,
                           EmployeeRepository employeeRepository,
                           PositionRepository positionRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      System.out.println("Iniciando carga de datos base");

      Position gerente = null;
      Position recepcionista = null;

      if (positionRepository.count() == 0) {
        gerente = new Position();
        gerente.setPositionName("Gerente");
        positionRepository.save(gerente);

        recepcionista = new Position();
        recepcionista.setPositionName("Recepcionista");
        positionRepository.save(recepcionista);

        System.out.println("Puestos creados");
      } else {
        gerente = positionRepository.findAll().get(0);
        recepcionista = positionRepository.findAll().get(1);
        System.out.println("Puestos ya creados");
      }

      if (employeeRepository.count() == 0) {
        Employee admin = new Employee();
        admin.setName("Super");
        admin.setLastName("Admin");
        admin.setEmail("admin@hotel.com");
        admin.setPassword("123");
        admin.setPhone("600000000");
        admin.setRole("ADMIN");
        admin.setPosition(gerente);
        employeeRepository.save(admin);

        Employee emp = new Employee();
        emp.setName("Juan");
        emp.setLastName("Recepcionista");
        emp.setEmail("empleado@hotel.com");
        emp.setPassword("123");
        emp.setPhone("600000001");
        emp.setRole("EMPLOYEE");
        emp.setPosition(recepcionista);
        employeeRepository.save(emp);

        System.out.println("Empleados base creados");
      } else {
        System.out.println("Empleados ya existentes");
      }

      if (clientRepository.count() == 0) {
        Client client = new Client();
        client.setDni("11122233C");
        client.setFirstName("Maria");
        client.setLastName("Cliente");
        client.setEmail("cliente@hotel.com");
        client.setPassword("123");
        client.setPhone("600000002");
        client.setRegistrationDate(LocalDateTime.now());
        clientRepository.save(client);

        System.out.println("Cliente base creado");
      } else {
        System.out.println("Clientes ya existentes");
      }
      System.out.println("Carga de datos completada");
    }
}
