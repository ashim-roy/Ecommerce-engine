package com.ashim.PaymentService.controller;


import com.ashim.PaymentService.dto.InitiatePaymentRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) {
        return null;
    }
}
