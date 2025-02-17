package com.example.excam_module4.service;

import com.example.excam_module4.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface IProductService extends IService<Product> {
    Page<Product> findAllProduct(Pageable pageable);
    Page<Product> searchProducts(String name, Integer typeId, BigDecimal price, Pageable pageable);
}

