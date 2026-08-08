package com.ashim.PaymentService.paymentGateway;

public interface PaymentGateway {
    String initiatePayment(Long orderId, String phoneNumber);
}
