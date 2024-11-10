package com.example.doganpoject1.repository;

import com.example.doganpoject1.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Belirli bir siparişe ait tüm sipariş öğelerini getirir
    List<OrderItem> findByOrderOrderId(Long orderId);
}
