package com.Ashim.CommerceEngine.repositories;

import com.Ashim.CommerceEngine.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long productid);
    //select * from Products where id = od from input param


    @Override
    List<Product> findAll();

    Product save(Product product);
}
