package com.Ashim.CommerceEngine;

import com.Ashim.CommerceEngine.productService.models.Category;
import com.Ashim.CommerceEngine.productService.models.Product;
import com.Ashim.CommerceEngine.productService.repositories.CategoryRepository;
import com.Ashim.CommerceEngine.productService.repositories.ProductRepository;
import com.Ashim.CommerceEngine.productService.repositories.projections.ProductWithTitleAndPrice;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository  categoryRepository;

	@Test
	void contextLoads() {
	}
	// test fetch one product with given ID
	@Disabled
	@Test
	void testQuerySingleProduct() {
		ProductWithTitleAndPrice productWithTitleAndPrice =
				productRepository.getTitleAndPriceById(5L);
		System.out.println(productWithTitleAndPrice.getTitle());
		System.out.println(productWithTitleAndPrice.getPrice());
	}

	@Test
	@Transactional
	void testQueryForListOfProducts() {
		List<ProductWithTitleAndPrice> productsWithTitleAndPrice =
				productRepository.getAllTitleAndPrice();

		for(ProductWithTitleAndPrice p: productsWithTitleAndPrice){
			System.out.println(p.getTitle());
			System.out.println(p.getPrice());
			System.out.println("-------------------------------------------------");
		}

		//categoryRepository.deleteById(1L);

		Optional<Category> optionalCategory = categoryRepository.findById(1L);

		List<Product> products = optionalCategory.get().getProducts();

		System.out.println("DEBUG");


	}

}
