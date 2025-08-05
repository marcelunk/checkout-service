package com.example.coupon_service.domain;

public class CheckoutItem {

    private final String productName;

    private final Double singlePrice;

    private final Integer quantity;

    private final Double originalPrice;

    private final Double newPrice;

    private final String description;

    public CheckoutItem(String productName, Double singlePrice, Integer quantity, Double originalPrice, Double newPrice,
            String description) {
        this.productName = productName;
        this.singlePrice = singlePrice;
        this.quantity = quantity;
        this.originalPrice = originalPrice;
        this.newPrice = newPrice;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\nSingle Price: %.2f Euro\nQuantity: %d\nOriginal Sum: %.2f Euro\nNew Sum: %.2f Euro\n%s\n-----",
                this.productName,
                this.singlePrice,
                this.quantity,
                this.originalPrice,
                this.newPrice,
                this.description);
    }

}
