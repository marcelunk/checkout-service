package com.example.coupon_service.api.dto;

import com.example.coupon_service.domain.Item;

import jakarta.validation.constraints.NotNull;

public record ItemDto(
        @NotNull String productId,
        @NotNull Integer quantity) {

    public Item toItem() {
        return new Item(productId, quantity);
    }

}
