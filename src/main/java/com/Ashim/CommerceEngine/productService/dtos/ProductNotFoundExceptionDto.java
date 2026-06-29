package com.Ashim.CommerceEngine.productService.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDto {
    private String message;
    private String resolution;
}
