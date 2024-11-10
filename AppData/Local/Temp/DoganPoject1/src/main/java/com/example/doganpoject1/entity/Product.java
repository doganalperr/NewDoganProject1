package com.example.doganpoject1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double productPrice;

    @Column(name = "stock", nullable = false)
    private int stock;

    // Stok azaltma metodu
    public boolean reduceStock(int quantity) {
        if (stock >= quantity) {
            stock -= quantity;
            return true;
        }
        return false;
    }

    // Stok artırma metodu
    public void increaseStock(int quantity) {
        stock += quantity;
    }

    // Getter ve Setter metodları
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
