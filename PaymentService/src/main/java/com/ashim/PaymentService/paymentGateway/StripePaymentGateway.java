package com.ashim.PaymentService.paymentGateway;


import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {

        // make a call to Stripe payment gateway in order to generate payment link
        //StripeClient stripeClient = new StripeClient(System.getenv("stripe.api.key"));
        StripeClient stripeClient = new StripeClient(apiKey); // Create Stripe client using the configured secret key

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(100000L)
                        .setProductData(
                                PriceCreateParams.ProductData.builder()
                                        .setName("iphone_charger")
                                        .build()
                        )
                        .build();

        Price price = null;
        try {
            price = stripeClient.prices().create(priceParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        /*   this is recent code as per their ocumentation, here we nee dto set product catalog
        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(100000L)  // price of each unit i s1000 rupees
                        .setProduct("prod_ABC123xyz")  // product id or name
                        .build();

// For SDK versions 29.4.0 or lower, remove '.v1()' from the following line.
        Price price;  // This price object you have to add here below:
        try {
            price = stripeClient.prices().create(priceParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }


         */


        PaymentLinkCreateParams paymentLinkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())  // Payment link for this price
                                        .setQuantity(1L)  // quantity I waat to order
                                        .build()
                        )
                        .build();

// For SDK versions 29.4.0 or lower, remove '.v1()' from the following line.
        // SDK <= 29.4.0 → no .v1()
        PaymentLink paymentLink = null;
        try {
            paymentLink = stripeClient.paymentLinks().create(paymentLinkParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

        return paymentLink.getUrl();

    }
}
