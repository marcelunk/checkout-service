package com.example.coupon_service.api.dto;

import jakarta.validation.constraints.NotNull;

public record ProductDto(
                @NotNull String productId,

                @NotNull String name,

                @NotNull String category,

                @NotNull Integer stock,

                @NotNull Double price) {
}
