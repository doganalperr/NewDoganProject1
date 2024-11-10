package com.example.doganpoject1.controller;

import com.example.doganpoject1.entity.Order;
import com.example.doganpoject1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place/{customerId}")
    public Order placeOrder(@PathVariable Long customerId) {
        return orderService.placeOrder(customerId);
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getOrderForCode(@PathVariable Long orderId) {
        return orderService.getOrderForCode(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
}
