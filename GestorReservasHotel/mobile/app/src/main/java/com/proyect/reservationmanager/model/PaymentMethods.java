package com.proyect.reservationmanager.model;

public class PaymentMethods {
    private Long idPaymentMethods;
    private String paymentName;

    public PaymentMethods() {}

    public PaymentMethods(Long idPaymentMethods, String paymentName) {
        this.idPaymentMethods = idPaymentMethods;
        this.paymentName = paymentName;
    }

    public Long getIdPaymentMethods() {
        return idPaymentMethods;
    }

    public void setIdPaymentMethods(Long idPaymentMethods) {
        this.idPaymentMethods = idPaymentMethods;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }
}
