package com.ashim.PaymentService.paymentGateway;


import org.springframework.stereotype.Service;

@Service
public class RazorPayPaymentGateway implements PaymentGateway {
    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {
        return null;
    }
}
