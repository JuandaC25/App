package com.app.backend.repository;

import com.app.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findBySubCategoryId(Long subcategoryId);
}
public class ProductRepository {
    
}
