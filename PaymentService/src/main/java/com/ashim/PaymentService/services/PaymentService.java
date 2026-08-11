package com.ashim.PaymentService.services;


import com.ashim.PaymentService.paymentGateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(Long orderId, String phoneNumber) {
        // make a call to paymentgateway to generate payment link
        return paymentGateway.initiatePayment(orderId, phoneNumber);
    }

}
