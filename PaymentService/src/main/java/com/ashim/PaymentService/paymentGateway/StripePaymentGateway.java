package com.ashim.PaymentService.paymentGateway;


import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {
        return null ;
        // make a call to Stripe payment gateway in order to generate payment link
    }
}
