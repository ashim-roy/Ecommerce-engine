package com.Ashim.CommerceEngine.exceptions;

public class ProductNotFoundException extends Exception {

      private Long productId;
//    public ProductNotFoundException(String message) {
//        super(message);
//    }

    public ProductNotFoundException(Long productId) {
        super("Product not found");
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
