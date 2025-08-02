package com.example.coupon_service.domain;

public class Order {

    private String orderId;

    private String date;

    private Long total;

    public Order(String orderId, String date, Long total) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDate() {
        return date;
    }

    public Long getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d", this.orderId, this.date, this.total);
    }

}
