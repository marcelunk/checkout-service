package com.example.checkout_service.domain;

import java.util.List;

public class Customer {

    private String customerId;

    private List<Order> orders;

    public Customer(String customerId, List<Order> orders) {
        this.customerId = customerId;
        this.orders = orders;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.customerId, this.orders);
    }

}
