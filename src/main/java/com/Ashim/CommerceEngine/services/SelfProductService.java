package com.Ashim.CommerceEngine.services;

import com.Ashim.CommerceEngine.exceptions.ProductNotFoundException;
import com.Ashim.CommerceEngine.models.Category;
import com.Ashim.CommerceEngine.models.Product;
import com.Ashim.CommerceEngine.repositories.CategoryRepository;
import com.Ashim.CommerceEngine.repositories.ProductRepository;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        //Product product = productRepository.findById(productId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with id: " + productId + " not found");
        }

       // String title = productOptional.get().getTitle();
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }



    @Override
    public Product createProduct(Product product) {
        long currentTime = System.currentTimeMillis();
        if (product.getCategory() != null) {
            if (product.getCategory().getId() == null) {
//                //Create a Category first.
                Category category = product.getCategory();

                category.setCreatedAt(currentTime);
                category.setLastUpdatedAt(currentTime);

                category = categoryRepository.save(category);
                product.setCategory(category);
            }
        } else {
            throw new RuntimeException("Category can't be empty while  creating a product");

        }
        product.setCreatedAt(currentTime);
        product.setLastUpdatedAt(currentTime);
        return productRepository.save(product);
    }
//
//                String categoryValue = category.getValue();
//
//                Optional<Category> optionalCategory = categoryRepository.findByValue(categoryValue);
//
//                if (optionalCategory.isEmpty()) {
//                    category = categoryRepository.save(category);
//                    product.setCategory(category);
//                } else {
//                    product.setCategory(optionalCategory.get());
//                }
//            }
//        } else {
//            throw new RuntimeException("Category can't be empty while creating a Product.");
//        }

                //  System.out.println("DEBUG");
                  //return productRepository.save(product);



    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public void updateProduct(Long productId, Product product) {

    }

    @Override
    public void replaceProduct(Long productId, Product product) {

    }
}
