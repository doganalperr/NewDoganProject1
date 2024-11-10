package com.example.doganpoject1.repository;

import com.example.doganpoject1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Belirli bir müşteri ID'sine göre müşteri bulur
    Optional<Customer> findByCustomerId(Long customerId);
}
