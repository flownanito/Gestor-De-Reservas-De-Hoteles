package com.proyect.reservationmanager.models;

public class Client {
  // Los nombres deben coincidir con tu JSON del Backend
  private Long id;
  private String dni;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phone;
  private String registrationDate; // String para simplificar la fecha por ahora

  // Constructor vacio necesario para Gson
  public Client() {
  }

  // Constructor completo
  public Client(String dni, String firstName, String lastName, String email, String password, String phone) {
    this.dni = dni;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phone = phone;
  }

  // Getters y Setters
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getDni() {
    return dni;
  }
  public void setDni(String dni) {
    this.dni = dni;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(String registrationDate) {
    this.registrationDate = registrationDate;
  }

  // Metodo toString para ver los datos en los logs fácilmente
  @Override
  public String toString() {
    return firstName + " " + lastName + " (" + email + ")";
  }
}
