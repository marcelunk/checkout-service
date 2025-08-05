package com.example.checkout_service.domain;

import java.util.List;

public class Checkout {

    private final String customerId;

    private final List<Item> basket;

    public Checkout(String customerId, List<Item> basket) {
        this.customerId = customerId;
        this.basket = basket;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public List<Item> getBasket() {
        return this.basket;
    }

}
