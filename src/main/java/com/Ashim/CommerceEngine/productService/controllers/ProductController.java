package com.Ashim.CommerceEngine.productService.controllers;


import com.Ashim.CommerceEngine.productService.exceptions.ProductNotFoundException;
import com.Ashim.CommerceEngine.productService.models.Product;
import com.Ashim.CommerceEngine.productService.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")  //https://localhost:8080/products/1 ==> geta  single product with id 1
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return new ResponseEntity<>(
                productService.getSingleProduct(productId),
                HttpStatus.OK
        );
    }

    @GetMapping        //https://localhost:8080/products/ ==>  get  all the products
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);

    }

    @DeleteMapping("/{id}")    //https://localhost:8080/products/1
    public void deleteProduct(@PathVariable("id") Long productId){

    }

    @PatchMapping("/{id}")     //partial update
    public Product replaceProduct(@PathVariable("id") Long productId,  @RequestBody Product product){
        return new Product();
    }

    @PutMapping("/{id}")    //full replace
    public Product updateProduct(@PathVariable("id") Long productId,  @RequestBody Product product){
        return new Product();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    private String handleProductNotFoundException(){
        return "Product not found";
    }


}
