package com.example.coupon_service.domain;

public class Product {

    private String productId;
    private String name;
    private String category;
    private Integer stock;
    private Double price;

    public Product(String productId, String name, String category, Integer stock, Double price) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d, %.2f", this.productId, this.name, this.category, this.stock, this.price);
    }

}
