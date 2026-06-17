package com.deepak.ProductService.controllers;


import com.deepak.ProductService.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")  //https://localhost:8080/products/1 ==> geta  single product with id 1
    public Product getSingleProduct(@PathVariable("id") Long productId){
            return new Product();
    }

    @GetMapping        ////https://localhost:8080/products/ ==>  get  all the products
    public List<Product> getAllProducts(){
        return new ArrayList<>();

    }


    @PostMapping
    public Product createProduct(){
        return new Product();

    }

    @DeleteMapping("/{id}")    //https://localhost:8080/products/1
    public void deleteProduct(@PathVariable("id") Long productId){

    }

    @PatchMapping("/{id}")     //partial update
    public Product replaceProduct(@PathVariable("id") Long productId,  @RequestBody Product product){

    }

    @PutMapping("/{id}")    //full replace
    public Product updateProduct(@PathVariable("id") Long productId,  @RequestBody Product product){


    }


}
