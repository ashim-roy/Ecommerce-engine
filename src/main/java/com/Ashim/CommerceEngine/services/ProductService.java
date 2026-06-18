package com.Ashim.CommerceEngine.services;

import com.Ashim.CommerceEngine.exceptions.ProductNotFoundException;
import com.Ashim.CommerceEngine.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    void deleteProduct(Long productId);

    void updateProduct(Long productId, Product product);

    void replaceProduct(Long productId, Product product);

}
