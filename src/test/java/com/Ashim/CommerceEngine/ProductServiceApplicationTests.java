package com.Ashim.CommerceEngine;

import com.Ashim.CommerceEngine.repositories.ProductRepository;
import com.Ashim.CommerceEngine.repositories.projections.ProductWithTitleAndPrice;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;

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
	void testQueryForListOfProducts() {
		List<ProductWithTitleAndPrice> productsWithTitleAndPrice =
				productRepository.getAllTitleAndPrice();

		for(ProductWithTitleAndPrice p: productsWithTitleAndPrice){
			System.out.println(p.getTitle());
			System.out.println(p.getPrice());
		}
	}

}
