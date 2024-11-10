package com.example.doganpoject1.repository;

import com.example.doganpoject1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Stokta bulunan ürünleri bulmak için
    Optional<Product> findByProductIdAndStockGreaterThan(Long productId, int stock);


    Optional<Product> findByProductId(Long productId);
}
