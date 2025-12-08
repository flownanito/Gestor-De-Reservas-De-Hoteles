package com.proyect.reservationmanager.model;

public class RegisterRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String dni;
  private String phone;

  public RegisterRequest(String firstName, String lastName, String email, String password, String dni, String phone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.dni = dni;
    this.phone = phone;
  }
}