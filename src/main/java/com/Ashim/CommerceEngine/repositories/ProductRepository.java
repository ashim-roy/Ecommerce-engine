package com.Ashim.CommerceEngine.repositories;

import com.Ashim.CommerceEngine.models.Product;
import com.Ashim.CommerceEngine.repositories.projections.ProductWithTitleAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long productid);
    //select * from Products where id = od from input param


    @Override
    List<Product> findAll();

    Product save(Product product);

    @Query("select p.title as title, p.price as price from products p where p.id= :id")
   // ProductWithTitleAndPrice  getTitleAndPriceById(Long productId);
     ProductWithTitleAndPrice  getTitleAndPriceById(@Param("id") Long productId);
}
