package com.example.coupon_service.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<String, Item> items;

    public Basket() {
        this.items = new HashMap<>();
    }

    public void addToBasket(Item item) {
        this.items.put(item.getProductId(), item);
    }

    public void increaseQuantity(String productId) {
        this.items.get(productId).increaseQuantity();
    }

    public void decreaseQuantity(String productId) {
        Integer quantity = this.items.get(productId).decreaseQuantity();
        if (quantity < 1) {
            this.items.remove(productId);
        }
    }

    public Collection<Item> getItems() {
        return this.items.values();
    }

}
