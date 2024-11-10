package com.example.doganpoject1.repository;

import com.example.doganpoject1.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Müşteriye ait sepeti bulur
    Optional<Cart> findByCustomerCustomerId(Long customerId);


}
