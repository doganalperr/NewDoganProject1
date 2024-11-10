package com.example.doganpoject1.repository;

import com.example.doganpoject1.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Sepet ve ürün ID'sine göre CartItem bulur
    Optional<CartItem> findByCartCartIdAndProductProductId(Long cartId, Long productId);

    // Belirli bir sepet ID'sine ait tüm öğeleri bulur
    List<CartItem> findAllByCartCartId(Long cartId);
}
