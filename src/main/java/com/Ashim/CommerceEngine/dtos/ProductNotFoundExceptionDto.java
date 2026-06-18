package com.Ashim.CommerceEngine.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDto {
    private String message;
    private String resolution;
}
