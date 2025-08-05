package com.example.checkout_service.api.dto;

import java.util.List;

import org.springframework.lang.NonNull;

import com.example.checkout_service.domain.CheckoutResult;

public record CheckoutPostResponseDto(
        @NonNull List<CheckoutItemDto> items,
        @NonNull Double totalSum) {

    public static CheckoutPostResponseDto of(CheckoutResult result) {
        return new CheckoutPostResponseDto(result.getItems().stream()
                .map(CheckoutItemDto::of).toList(),
                result.getTotalSum());
    }

}
