package com.example.checkout_service.api.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto(

                @NotNull String orderId,

                @NotNull String date,

                @NotNull Double total

) {
}
