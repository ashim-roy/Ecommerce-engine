package com.Ashim.CommerceEngine.controllers;


import com.Ashim.CommerceEngine.models.Product;
import com.Ashim.CommerceEngine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")  //https://localhost:8080/products/1 ==> geta  single product with id 1
    public Product getSingleProduct(@PathVariable("id") Long productId){
            return productService.getSingleProduct(productId);
    }

    @GetMapping        //https://localhost:8080/products/ ==>  get  all the products
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return new Product();

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


}
