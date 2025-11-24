package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.Payment;
import com.proyect.reservationmanager.model.PaymentMethods;
import com.proyect.reservationmanager.repository.PaymentRepository;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
  
  @Autowired
  private PaymentRepository paymentRepository;

  // Endpoint: GET /api/payments
  @GetMapping
  public ResponseEntity<List<Payment>> getAllPayments() {
    List<Payment> payments = paymentRepository.findAll();
    return new ResponseEntity<>(payments, HttpStatus.OK);
  }
  // Endpoint: GET /api/payments/{id}
  @GetMapping("/{id}")
  public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
    Optional<Payment> payment = paymentRepository.findById(id);
    return payment.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Endpoint: POST /api/payments
  @PostMapping
  public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
    Payment savedPayment = paymentRepository.save(payment);
    return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
  }

  // Endpoint: PUT /api/payments/{id}
  @PutMapping("/{id}")
  public ResponseEntity<Payment> updatePayment(
      @PathVariable Long id, 
      @RequestBody Payment paymentDetails) {
    
    return paymentRepository.findById(id)
      .map(payment -> {
        // Actualizamos los campos con los datos del JSON
        payment.setStatus(paymentDetails.getStatus());
        payment.setAmount(paymentDetails.getAmount());
        payment.setPaymentDate(paymentDetails.getPaymentDate());

        // Guardamos la entidad actualizada
        Payment updatedPayment = paymentRepository.save(payment);
        return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  // Endpoint: DELETE /api/payments/{id}
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
    if (paymentRepository.existsById(id)) {
      paymentRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Endpoint adicional: GET /api/payments/status/{status}
  // Buscar pagos por estado (ejemplo: "COMPLETED", "PENDING", "FAILED")
  @GetMapping("/status/{status}")
  public List<Payment> getPaymentsByStatus(@PathVariable String status) {
    return paymentRepository.findByStatus(status);
  }
}