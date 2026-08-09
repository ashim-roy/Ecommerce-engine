package com.ashim.PaymentService.paymentGateway;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {
        return null ;
        // make a call to Stripe payment gateway in order to generate payment link
    }
}
