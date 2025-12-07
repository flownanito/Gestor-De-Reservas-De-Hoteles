package com.proyect.reservationmanager.api;

import com.proyect.reservationmanager.models.LoginRequest;
import com.proyect.reservationmanager.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

  @POST("api/auth/login")
  Call<LoginResponse> login(@Body LoginRequest request);
}
