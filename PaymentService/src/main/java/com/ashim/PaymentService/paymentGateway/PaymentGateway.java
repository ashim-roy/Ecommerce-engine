package com.ashim.PaymentService.paymentGateway;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String initiatePayment(Long orderId, String phoneNumber);
}
