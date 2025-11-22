package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.Payment;
import com.proyect.reservationmanager.repository.PaymentRepository;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  @Autowired
  private PaymentRepository paymentRepository;

  @GetMapping
  public ResponseEntity<List<Payment>> getAllPayments() {
    List<Payment> payments = paymentRepository.findAll();
    return new ResponseEntity<>(payments, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
    Optional<Payment> payment = paymentRepository.findById(id);
    return payment.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
    Payment saved = paymentRepository.save(payment);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
    return paymentRepository.findById(id)
      .map(payment -> {
        payment.setPaymentName(paymentDetails.getPaymentName());
        Payment updated = paymentRepository.save(payment);
        return new ResponseEntity<>(updated, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
    if (paymentRepository.existsById(id)) {
      paymentRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
