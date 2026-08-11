package com.ashim.PaymentService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class StripeWebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(
            @RequestBody String payload) {

        System.out.println("========== STRIPE WEBHOOK ==========");
        System.out.println(payload);
        System.out.println("====================================");

        return ResponseEntity.ok("Webhook received");
    }


}

// POST http://localhost:8080/payments/webhook
/*

// Webhook endpoint called by Stripe after a payment event.
// Stripe CLI forwards Stripe events to this endpoint during local development.
// Example: payment_intent.succeeded
// Later, we will verify the webhook signature and update payment/order status.

Key Takeaway

Redirect URL is for the customer's browser. Webhook is for backend-to-backend communication and payment processing.

For local development:

stripe listen --forward-to http://localhost:8080/payments/webhook

Then test with:

stripe trigger payment_intent.succeeded

**One correction to keep in your notes:** your application is currently running on **8080**, so the webhook endpoint should be `http://localhost:8080/payments/webhook`. Your earlier success redirect was `9000`, which is fine only if your separate endpoint/application is actually running on port 9000.

 */
