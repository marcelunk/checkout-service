package com.example.coupon_service.domain;

import java.util.ArrayList;
import java.util.List;

public class CheckoutResult {

    private List<CheckoutItem> items;

    private Double totalSum;

    public CheckoutResult() {
        this.items = new ArrayList<>();
    }

    public CheckoutResult(List<CheckoutItem> items) {
        this.items = items;
    }

    public void appendItem(CheckoutItem item) {
        this.items.addLast(item);
    }

    public void checkout() {
        this.totalSum = this.items.stream().mapToDouble(CheckoutItem::getNewPrice).sum();
    }

    public List<CheckoutItem> getItems() {
        return items;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (CheckoutItem item : items) {
            builder.append(item);
            builder.append("\n");
        }

        builder.append(String.format("Total sum: %.2f Euro", this.totalSum));

        return builder.toString();
    }

}
