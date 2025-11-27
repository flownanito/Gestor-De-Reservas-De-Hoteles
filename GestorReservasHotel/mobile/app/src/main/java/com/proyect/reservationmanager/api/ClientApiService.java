package com.proyect.reservationmanager.api;

import com.proyect.reservationmanager.models.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientApiService {
  // Get All
  @GET("api/clients")
  Call<List<Client>> getAllClients();

  // Get by id
  @GET("api/clients/{id}")
  Call<Client> getClientById(@Path("id") Long id);

  // Post (El @body envía el objeto Client cono JSON)
  @POST("api/clients")
  Call<Client> createClient(@Body Client client);

  // Put
  @PUT("api/clients/{id}")
  Call<Client> updateClient(@Path("id") Long id, @Body Client client);

  // Delete
  @DELETE("api/clients/{id}")
  Call<Void> deleteClient(@Path("id") Long id);
}
