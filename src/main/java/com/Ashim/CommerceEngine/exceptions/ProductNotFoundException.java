package com.Ashim.CommerceEngine.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {

     // private final Long productId;
    public ProductNotFoundException(String message) {
        super(message);
    }

//    public ProductNotFoundException(Long productId) {
//        super("Product not found");
//        this.productId = productId;
//    }

}
