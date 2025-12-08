package com.proyect.reservationmanager.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  // Usamos 10.0.2.2 para apuntar al localhost de tu PC desde el emulador
  // Asegúrate de terminar la URL con una barra '/'
  // Como uso mi movil fisico uso http://localhost:8080/
  private static final String BASE_URL = "http://localhost:8080/";

  private static RetrofitClient instance = null;
  private Retrofit retrofit;

  private RetrofitClient() {
    retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public static synchronized RetrofitClient getInstance() {
    if (instance == null) {
      instance = new RetrofitClient();
    }
    return instance;
  }

  public ApiService getApi() {
    return retrofit.create(ApiService.class);
  }

  public ClientApiService getClientApi() {
    return retrofit.create(ClientApiService.class);
  }
}
