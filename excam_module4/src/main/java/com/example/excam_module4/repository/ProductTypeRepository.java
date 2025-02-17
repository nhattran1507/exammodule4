package com.example.excam_module4.repository;

import com.example.excam_module4.model.Product;
import com.example.excam_module4.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
}
