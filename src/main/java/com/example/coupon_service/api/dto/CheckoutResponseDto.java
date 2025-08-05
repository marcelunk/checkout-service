package com.example.coupon_service.api.dto;

import java.util.List;

import org.springframework.lang.NonNull;

import com.example.coupon_service.domain.CheckoutResult;

public record CheckoutResponseDto(
        @NonNull List<CheckoutItemDto> items,
        @NonNull Double totalSum) {

    public static CheckoutResponseDto of(CheckoutResult result) {
        return new CheckoutResponseDto(result.getItems().stream()
                .map(CheckoutItemDto::of).toList(),
                result.getTotalSum());
    }

}
