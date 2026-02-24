package com.proyect.reservationmanager.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.model.Employee;
import com.proyect.reservationmanager.model.Position;
import com.proyect.reservationmanager.model.RoomType;
import com.proyect.reservationmanager.repository.ClientRepository;
import com.proyect.reservationmanager.repository.EmployeeRepository;
import com.proyect.reservationmanager.repository.PositionRepository;
import com.proyect.reservationmanager.repository.RoomTypeRepository;

@Component
public class DataInitializer implements CommandLineRunner {
  private final ClientRepository clientRepository;
  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;
  private final RoomTypeRepository roomTypeRepository;

  public DataInitializer(ClientRepository clientRepository,
      EmployeeRepository employeeRepository,
      PositionRepository positionRepository,
      RoomTypeRepository roomTypeRepository) {
    this.clientRepository = clientRepository;
    this.employeeRepository = employeeRepository;
    this.positionRepository = positionRepository;
    this.roomTypeRepository = roomTypeRepository;
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

    System.out.println("RoomType count: " + roomTypeRepository.count());

    if (roomTypeRepository.count() == 0) {
      System.out.println("Insertando RoomTypes...");
      RoomType deluxe = new RoomType();
      deluxe.setName("Habitación Deluxe");
      deluxe.setBasePrice(new BigDecimal("150"));
      deluxe.setDescription("Espaciosa habitación con vista a la ciudad y todas las comodidades modernas.");
      deluxe.setNumBeds(1);
      deluxe.setCapacity(2);
      deluxe.setTotalRooms(10);
      deluxe.setAvailableRooms(10);
      roomTypeRepository.save(deluxe);

      RoomType superior = new RoomType();
      superior.setName("Habitación Superior");
      superior.setBasePrice(new BigDecimal("120"));
      superior.setDescription("Habitación confortable con cama king size y escritorio de trabajo.");
      superior.setNumBeds(1);
      superior.setCapacity(2);
      superior.setTotalRooms(10);
      superior.setAvailableRooms(10);
      roomTypeRepository.save(superior);

      RoomType suiteEjecutiva = new RoomType();
      suiteEjecutiva.setName("Suite Ejecutiva");
      suiteEjecutiva.setBasePrice(new BigDecimal("280"));
      suiteEjecutiva.setDescription("Suite premium con sala de estar separada y vistas panorámicas excepcionales.");
      suiteEjecutiva.setNumBeds(2);
      suiteEjecutiva.setCapacity(3);
      suiteEjecutiva.setTotalRooms(5);
      suiteEjecutiva.setAvailableRooms(5);
      roomTypeRepository.save(suiteEjecutiva);

      RoomType familiar = new RoomType();
      familiar.setName("Habitación Familiar");
      familiar.setBasePrice(new BigDecimal("200"));
      familiar.setDescription("Perfecta para familias, con dos habitaciones conectadas y espacio amplio.");
      familiar.setNumBeds(3);
      familiar.setCapacity(4);
      familiar.setTotalRooms(5);
      familiar.setAvailableRooms(5);
      roomTypeRepository.save(familiar);

      RoomType estandar = new RoomType();
      estandar.setName("Habitación Estándar");
      estandar.setBasePrice(new BigDecimal("90"));
      estandar.setDescription("Cómoda y acogedora, ideal para estancias cortas con todas las comodidades básicas.");
      estandar.setNumBeds(1);
      estandar.setCapacity(2);
      estandar.setTotalRooms(15);
      estandar.setAvailableRooms(15);
      roomTypeRepository.save(estandar);

      RoomType suitePresidencial = new RoomType();
      suitePresidencial.setName("Suite Presidencial");
      suitePresidencial.setBasePrice(new BigDecimal("280"));
      suitePresidencial.setDescription("La máxima expresión de lujo con servicios exclusivos y mayordomo personal.");
      suitePresidencial.setNumBeds(2);
      suitePresidencial.setCapacity(2);
      suitePresidencial.setTotalRooms(3);
      suitePresidencial.setAvailableRooms(3);
      roomTypeRepository.save(suitePresidencial);

      RoomType jacuzzi = new RoomType();
      jacuzzi.setName("Habitación con Jacuzzi");
      jacuzzi.setBasePrice(new BigDecimal("200"));
      jacuzzi.setDescription("Perfecta para huespedes que buscan relajarse con jacuzzi privado.");
      jacuzzi.setNumBeds(1);
      jacuzzi.setCapacity(2);
      jacuzzi.setTotalRooms(2);
      jacuzzi.setAvailableRooms(2);
      roomTypeRepository.save(jacuzzi);

      RoomType terraza = new RoomType();
      terraza.setName("Habitación con Terraza");
      terraza.setBasePrice(new BigDecimal("90"));
      terraza.setDescription("Cómoda y acogedora, ideal para estancias cortas con todas las comodidades básicas.");
      terraza.setNumBeds(1);
      terraza.setCapacity(2);
      terraza.setTotalRooms(4);
      terraza.setAvailableRooms(4);
      roomTypeRepository.save(terraza);
    }
    System.out.println("Carga de datos completada");
  }
}
