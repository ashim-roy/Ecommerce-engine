package com.ashim.PaymentService.controller;


import com.ashim.PaymentService.dto.InitiatePaymentRequestDto;
import com.ashim.PaymentService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")  // POST http://localhost:9000/payments/initiate
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) {
        //
        return paymentService.initiatePayment(requestDto.getOrderId(), requestDto.getPhoneNumber());
    }
}
