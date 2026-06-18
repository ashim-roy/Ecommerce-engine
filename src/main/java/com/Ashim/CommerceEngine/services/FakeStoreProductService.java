package com.Ashim.CommerceEngine.services;

import com.Ashim.CommerceEngine.dtos.FakeStoreProductDto;
import com.Ashim.CommerceEngine.exceptions.ProductNotFoundException;
import com.Ashim.CommerceEngine.models.Category;
import com.Ashim.CommerceEngine.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws  ProductNotFoundException {
       // RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );
        // convert FakeStoreProductDto obj  to product obj
        assert fakeStoreProductDto != null;
        return convertFakeStoreStoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreStoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setCategory(new Category());
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());

        product.getCategory().setValue(fakeStoreProductDto.getCategory());
        return product;

    }

    @Override
    public List<Product> getAllProducts() {
        //Type Erasure
        FakeStoreProductDto [] fakeStoreProductDtos = restTemplate.getForObject(
                        "https://fakestoreapi.com/products/",
                         FakeStoreProductDto[].class
                );

        List<Product> products = new ArrayList<>();
        assert fakeStoreProductDtos != null;
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreStoToProduct(fakeStoreProductDto));
        }

        return products;
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
