package com.example.coupon_service.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record CustomerDto(

        @NotNull String customerId,

        @NotNull List<OrderDto> orders

) {
}
