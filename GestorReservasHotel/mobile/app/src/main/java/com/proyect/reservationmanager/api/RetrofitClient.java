package com.proyect.reservationmanager.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  // Usamos 10.0.2.2 para apuntar al localhost de tu PC desde el emulador
  // Asegúrate de terminar la URL con una barra '/'
  // Como uso mi movil fisico uso http://localhost:8080/
  private static final String BASE_URL = "http://localhost:8080/";

  private static Retrofit retrofit = null;

  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
    }
    return retrofit;
  }
}
