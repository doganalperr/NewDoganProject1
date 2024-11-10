package com.example.doganpoject1.service;

import com.example.doganpoject1.entity.Customer;
import com.example.doganpoject1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // AddCustomer: Yeni müşteri ekler
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Müşteri ID'sine göre müşteri bilgilerini getirir
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}
