package com.Ashim.CommerceEngine.productService.services;

import com.Ashim.CommerceEngine.productService.dtos.FakeStoreProductDto;
import com.Ashim.CommerceEngine.productService.exceptions.ProductNotFoundException;
import com.Ashim.CommerceEngine.productService.models.Category;
import com.Ashim.CommerceEngine.productService.models.Product;
import com.Ashim.CommerceEngine.productService.repositories.projections.ProductWithTitleAndPrice;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
@Primary
public class FakeStoreProductService implements ProductService {
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId)
            throws  ProductNotFoundException {

        System.out.println("🔥 FAKE STORE SERVICE CALLED");

        // let's make a call to userService
        System.out.println("🔥 Calling UserService...");
        restTemplate.getForObject("http://localhost:9000/users/sample", Void.class);

        // Fetch product from REDIS
        // 1. Check Redis
        Product cachedProduct = (Product)redisTemplate.opsForHash().get("products", "products_" + productId);

        // 2. Cache hit
        if (cachedProduct != null) {
            System.out.println("🔥 Product found in Redis cache for productId: " + productId);
            return cachedProduct;
        }

        // 3. Cache miss → call FakeStore
        // ELSE -if roduct is not present in Redis then fetch form fakestore DB and store in REDIS and return
       //
        FakeStoreProductDto fSPDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );

        // 4. Convert DTO → Product
        if (fSPDto == null) {
            throw new ProductNotFoundException("Product with id: " + productId + " not found");
        }

        Product product = convertFakeStoreDtoToProduct(fSPDto);


        // 5. Store Product in Redis
        redisTemplate.opsForHash().put("products", "products_" + productId, product);


        // 6. Return
        return product;
        // convert FakeStoreProductDto obj  to product obj
       // assert fakeStoreProductDto != null;
        //return convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
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

    /*
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
    }*/

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        //Type Erasure
        FakeStoreProductDto [] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class
        );

        List<Product> products = new ArrayList<>();
        assert fakeStoreProductDtos != null;
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }

        return new PageImpl<>(products);
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

    @Override
    public List<ProductWithTitleAndPrice> getProductSummaries() {
        return List.of();
    }
}
