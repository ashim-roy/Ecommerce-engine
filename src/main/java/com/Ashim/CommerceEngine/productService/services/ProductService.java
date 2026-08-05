package com.Ashim.CommerceEngine.productService.services;

import com.Ashim.CommerceEngine.productService.exceptions.ProductNotFoundException;
import com.Ashim.CommerceEngine.productService.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    //List<Product> getAllProducts();
    // below IMPL for pagination
    Page<Product> getAllProducts(int pageNumber, int pageSize);

    Product createProduct(Product product);

    void deleteProduct(Long productId);

    void updateProduct(Long productId, Product product);

    void replaceProduct(Long productId, Product product);

}
