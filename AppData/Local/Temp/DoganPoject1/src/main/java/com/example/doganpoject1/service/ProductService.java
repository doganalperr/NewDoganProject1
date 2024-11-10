package com.example.doganpoject1.service;

import com.example.doganpoject1.entity.Product;
import com.example.doganpoject1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Ürün ekleme metodu
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Ürün güncelleme metodu
    public Product updateProduct(Long productId, Product updatedProductData) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        product.setProductName(updatedProductData.getProductName());
        product.setProductPrice(updatedProductData.getProductPrice());
        product.setStock(updatedProductData.getStock());


        return productRepository.save(product);
    }

    // Ürün silme metodu
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // Ürün detaylarını getirme metodu
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}
