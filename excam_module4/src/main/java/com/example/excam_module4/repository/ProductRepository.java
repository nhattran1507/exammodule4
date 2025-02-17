package com.example.excam_module4.repository;

import com.example.excam_module4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:typeId IS NULL OR p.productType.id = :typeId) " +
            "AND (:price IS NULL OR p.price = :price)")
    Page<Product> searchProducts(@Param("name") String name,
                                 @Param("typeId") Integer typeId,
                                 @Param("price") BigDecimal price,
                                 Pageable pageable);
}
