package com.Ashim.CommerceEngine.services;

import com.Ashim.CommerceEngine.dtos.FakeStreProductDto;
import com.Ashim.CommerceEngine.models.Category;
import com.Ashim.CommerceEngine.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = new RestTemplate();
        FakeStreProductDto fakeStreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStreProductDto.class
        );
        // convert FakeStreProductDto obj  to product obj
        return convertFakeStoreStoToProduct(fakeStreProductDto);
    }

    private Product convertFakeStoreStoToProduct(FakeStreProductDto fakeStreProductDto) {
        Product product = new Product();
        product.setCategory(new Category());
        product.setId(fakeStreProductDto.getId());
        product.setDescription(fakeStreProductDto.getDescription());
        product.setImage(fakeStreProductDto.getImage());
        product.setPrice(fakeStreProductDto.getPrice());
        product.setTitle(fakeStreProductDto.getTitle());

        product.getCategory().setValue(fakeStreProductDto.getCategory());
        return product;

    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

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
