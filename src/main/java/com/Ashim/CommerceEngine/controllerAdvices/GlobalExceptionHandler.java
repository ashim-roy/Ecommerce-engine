package com.Ashim.CommerceEngine.controllerAdvices;


import com.Ashim.CommerceEngine.dtos.ProductNotFoundExceptionDto;
import com.Ashim.CommerceEngine.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ProductNotFoundExceptionDto>  handleProductNotFoundException() {

        ProductNotFoundExceptionDto pnfdto = new ProductNotFoundExceptionDto();
        pnfdto.setMessage("Product Not Found with the given id");
        pnfdto.setResolution("please try passing a valid productId");

        return new ResponseEntity<>(
                pnfdto,
                HttpStatus.NOT_FOUND
        );

    }
}
