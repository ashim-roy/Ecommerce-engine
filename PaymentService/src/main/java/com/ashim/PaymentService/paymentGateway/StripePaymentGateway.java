package com.ashim.PaymentService.paymentGateway;


import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {

        // make a call to Stripe payment gateway in order to generate payment link
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice("{{PRICE_ID}}")
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

// For SDK versions 29.4.0 or lower, remove '.v1()' from the following line.
        PaymentLink paymentLink = client.v1().paymentLinks().create(params);

        return null ;

    }
}
