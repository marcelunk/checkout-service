package com.example.coupon_service.api.dto;

import java.util.List;

import com.example.coupon_service.domain.Checkout;

import jakarta.validation.constraints.NotNull;

public record CheckoutRequestDto(
        @NotNull String customerId,
        @NotNull List<ItemDto> items) {

    public Checkout toCheckout() {
        return new Checkout(customerId, items.stream().map(ItemDto::toItem).toList());
    }

}
