package com.proyect.reservationmanager.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
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
