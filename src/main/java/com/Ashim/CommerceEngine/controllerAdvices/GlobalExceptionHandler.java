package com.Ashim.CommerceEngine.controllerAdvices;


import com.Ashim.CommerceEngine.dtos.ProductNotFoundExceptionDto;
import com.Ashim.CommerceEngine.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Global exception handler


    //Global Exception Handler.
    @ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException() {
        ProductNotFoundExceptionDto dto = new ProductNotFoundExceptionDto();
        dto.setMessage("Product not found with the given id.");
        dto.setResolution("Please try passing a valid productId.");

        return new ResponseEntity<>(
                dto,
                HttpStatus.UNAUTHORIZED
        );
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ProductNotFoundExceptionDto>  handleProductNotFoundException() {
//
//        ProductNotFoundExceptionDto pnfdto = new ProductNotFoundExceptionDto();
//
//        pnfdto.setMessage("Product with id "   + ex.getProductId()   + " not found");
//        pnfdto.setResolution("please try passing a valid productId");
//
//        return new ResponseEntity<>(
//                pnfdto,
//                HttpStatus.NOT_FOUND
//        );
//    }


   // @ExceptionHandler(RuntimeException.class)
    //public void handleRuntimeException() {

    //}

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    private void handleArrayIndexOutOfBoundException(){

    }
}


