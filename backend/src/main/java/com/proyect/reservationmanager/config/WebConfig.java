package com.proyect.reservationmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  public WebConfig() {
    System.out.println("Cargando configuracion cors");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Aplica a todos los endpoints
      .allowedOrigins("http://localhost:5173") // Permite solo al front de react
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodos permitidos
      .allowedHeaders("*")
      .allowCredentials(true);
  }
}
