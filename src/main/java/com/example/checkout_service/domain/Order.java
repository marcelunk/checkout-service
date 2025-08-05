package com.example.checkout_service.domain;

public class Order {

    private String orderId;

    private Date date;

    private Double total;

    public Order(String orderId, String date, Double total) {
        this.orderId = orderId;
        String[] split = date.split("-");
        this.date = new Date(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public Double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %.2f", this.orderId, this.date, this.total);
    }

}
