package com.ashim.PaymentService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
    private long orderid;
    private String phoneNumber;
}
