package com.example.doganpoject1.service;

import com.example.doganpoject1.entity.Cart;
import com.example.doganpoject1.entity.CartItem;
import com.example.doganpoject1.entity.Product;
import com.example.doganpoject1.repository.CartItemRepository;
import com.example.doganpoject1.repository.CartRepository;
import com.example.doganpoject1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // GetCart: Müşterinin sepetini getirir
    public Optional<Cart> getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerCustomerId(customerId);
    }

    // EmptyCart: Sepeti tamamen boşaltır
    public void emptyCart(Long cartId) {
        cartItemRepository.findAllByCartCartId(cartId).forEach(cartItem -> {
            Product product = cartItem.getProduct();
            product.increaseStock(cartItem.getQuantity());
            productRepository.save(product);
            cartItemRepository.delete(cartItem);
        });
        updateTotalPrice(cartId);
    }

    // AddProductToCart: Sepete ürün ekler veya ürün ekli ise miktarını arttırır
    public void addProductToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!product.reduceStock(quantity)) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        productRepository.save(product);

        CartItem cartItem = cartItemRepository.findByCartCartIdAndProductProductId(cartId, productId)
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    newItem.setQuantity(0);
                    newItem.setPriceAtAddition(product.getProductPrice());
                    return newItem;
                });

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
        updateTotalPrice(cartId);
    }

    // RemoveProductFromCart: Sepetten ürünü çıkarır veya miktarını azaltır
    public void removeProductFromCart(Long cartId, Long productId, int quantity) {
        CartItem cartItem = cartItemRepository.findByCartCartIdAndProductProductId(cartId, productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        Product product = cartItem.getProduct();
        int currentQuantity = cartItem.getQuantity();

        if (quantity >= currentQuantity) {
            cartItemRepository.delete(cartItem);
            product.increaseStock(currentQuantity);
        } else {
            cartItem.setQuantity(currentQuantity - quantity);
            product.increaseStock(quantity);
            cartItemRepository.save(cartItem);
        }
        productRepository.save(product);
        updateTotalPrice(cartId);
    }

    // UpdateCart: Sepet toplam fiyatını günceller
    private void updateTotalPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(cartItem -> cartItem.getPriceAtAddition() * cartItem.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }
}
