package com.Ashim.CommerceEngine.repositories;

import com.Ashim.CommerceEngine.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

  //  @Override
   // static Optional<Category> findById(Long aLong);  // JpaRepository already gives you these methods for free:

    Category save(Category category);

    Optional<Category> findByValue(String value);

    @Override
    void deleteById(Long categoryId);
}
