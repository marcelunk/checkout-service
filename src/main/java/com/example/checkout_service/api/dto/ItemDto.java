package com.example.checkout_service.api.dto;

import com.example.checkout_service.domain.Item;

import jakarta.validation.constraints.NotNull;

public record ItemDto(
        @NotNull String productId,
        @NotNull Integer quantity) {

    public Item toItem() {
        return new Item(productId, quantity);
    }

}
