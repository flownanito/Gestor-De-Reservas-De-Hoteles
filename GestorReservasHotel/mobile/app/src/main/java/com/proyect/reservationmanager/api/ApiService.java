package com.proyect.reservationmanager.api;

import com.proyect.reservationmanager.models.LoginRequest;
import com.proyect.reservationmanager.models.LoginResponse;
import com.proyect.reservationmanager.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

  @POST("api/auth/login")
  Call<LoginResponse> login(@Body LoginRequest request);

  @POST("api/auth/register")
  Call<Void> register(@Body RegisterRequest request);
}
