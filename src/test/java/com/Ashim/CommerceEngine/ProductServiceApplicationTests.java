package com.Ashim.CommerceEngine;

import com.Ashim.CommerceEngine.repositories.ProductRepository;
import com.Ashim.CommerceEngine.repositories.projections.ProductWithTitleAndPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testQuery() {
		ProductWithTitleAndPrice productWithTitleAndPrice =
				productRepository.getTitleAndPriceById(5L);
		System.out.println(productWithTitleAndPrice.getTitle());
		System.out.println(productWithTitleAndPrice.getPrice());
	}

}
