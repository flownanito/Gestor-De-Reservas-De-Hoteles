package com.proyect.reservationmanager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Indica que esta clase maneja excepciones para todos los controladores
public class GlobalExceptionHandler {
  // Este método captura la excepción que se lanza cuando falla @Valid en un @RequestBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        // Creamos un mapa para almacenar los errores de forma limpia
        Map<String, String> errors = new HashMap<>();

        // Recorremos todos los errores de campo que detectó Spring
        ex.getBindingResult().getAllErrors().forEach((error) -> {
          String fieldName = ((FieldError) error).getField();
          // Obtemenos el mensaje de error
          String errorMessage = error.getDefaultMessage();
          errors.put(fieldName, errorMessage);
        });

        // Devolvemos el código 400 Bad Request con el mapa de errores detallado
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
      }
}
