package com.example.coupon_service.domain;

public class Item {

    private String productId;

    private Integer quantity;

    public Item(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer increaseQuantity() {
        return ++quantity;
    }

    public Integer decreaseQuantity() {
        return --quantity;
    }

    @Override
    public String toString() {
        return String.format("Item: %s, %s", productId, quantity);
    }

}
