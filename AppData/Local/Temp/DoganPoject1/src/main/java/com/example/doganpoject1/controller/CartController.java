package com.example.doganpoject1.controller;

import com.example.doganpoject1.entity.Cart;
import com.example.doganpoject1.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/customer/{customerId}")
    public Optional<Cart> getCart(@PathVariable Long customerId) {
        return cartService.getCartByCustomerId(customerId);
    }

    @PutMapping("/add/{cartId}/product/{productId}")
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        cartService.addProductToCart(cartId, productId, quantity);
    }

    @PutMapping("/remove/{cartId}/product/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int quantity) {
        cartService.removeProductFromCart(cartId, productId, quantity);
    }

    @DeleteMapping("/empty/{cartId}")
    public void emptyCart(@PathVariable Long cartId) {
        cartService.emptyCart(cartId);
    }
}
