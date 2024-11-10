package com.example.doganpoject1.repository;

import com.example.doganpoject1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Belirli bir müşteriye ait tüm siparişleri getirir
    List<Order> findAllByCustomerCustomerId(Long customerId);

    // Siparişe göre sipariş detaylarını getirir
    Optional<Order> findByOrderId(Long orderId);
}
