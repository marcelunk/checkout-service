package com.example.checkout_service.api.dto;

import org.springframework.lang.NonNull;

import com.example.checkout_service.domain.CheckoutItem;

public record CheckoutItemDto(
        @NonNull String productName,

        @NonNull Double singlePrice,

        @NonNull Integer quantity,

        @NonNull Double originalPrice,

        @NonNull Double newPrice,

        @NonNull String description) {
    public static CheckoutItemDto of(CheckoutItem item) {
        return new CheckoutItemDto(item.getProductName(), item.getSinglePrice(), item.getQuantity(),
                item.getOriginalPrice(), item.getOriginalPrice(), item.getDescription());
    }
}
