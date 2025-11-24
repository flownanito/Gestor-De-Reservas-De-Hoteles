package com.proyect.reservationmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.reservationmanager.model.PaymentMethods;
import com.proyect.reservationmanager.repository.PaymentMethodsRepository;

@RestController
@RequestMapping("/api/paymentmethods")
public class PaymentMethodsController {

  @Autowired
  private PaymentMethodsRepository paymentMethodsRepository;

  @GetMapping
  public ResponseEntity<List<PaymentMethods>> getAllPaymentMethods() {
    List<PaymentMethods> paymentsmethod = paymentMethodsRepository.findAll();
    return new ResponseEntity<>(paymentsmethod, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentMethods> getPaymentById(@PathVariable Long id) {
    Optional<PaymentMethods> payment = paymentMethodsRepository.findById(id);
    return payment.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<PaymentMethods> createPayment(@RequestBody PaymentMethods payment) {
    PaymentMethods saved = paymentMethodsRepository.save(payment);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PaymentMethods> updatePayment(@PathVariable Long id, @RequestBody PaymentMethods paymentDetails) {
    return paymentMethodsRepository.findById(id)
      .map(payment -> {
        payment.setPaymentName(paymentDetails.getPaymentName());
        PaymentMethods updated = paymentMethodsRepository.save(payment);
        return new ResponseEntity<>(updated, HttpStatus.OK);
      })
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
    if (paymentMethodsRepository.existsById(id)) {
      paymentMethodsRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
