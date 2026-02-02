package com.proyect.reservationmanager.api;

import com.proyect.reservationmanager.model.Client;
import com.proyect.reservationmanager.model.LoginRequest;
import com.proyect.reservationmanager.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

  @POST("api/auth/login")
  Call<LoginResponse> login(@Body LoginRequest request);

  // GET - Obtener todos los clientes
  @GET("api/clients")
  Call<List<Client>> getAllClients();

  // GET - Obtener cliente por ID
  @GET("api/clients/{id}")
  Call<Client> getClientById(@Path("id") Long id);

  // GET - Obtener cliente por DNI
  @GET("api/clients/dni/{dni}")
  Call<Client> getClientByDni(@Path("dni") String dni);

  // POST - Crear nuevo cliente
  @POST("api/clients")
  Call<Client> createClient(@Body Client client);

  // PUT - Actualizar cliente
  @PUT("api/clients/{id}")
  Call<Client> updateClient(@Path("id") Long id, @Body Client client);

  // DELETE - Eliminar cliente
  @DELETE("api/clients/{id}")
  Call<Void> deleteClient(@Path("id") Long id);
}