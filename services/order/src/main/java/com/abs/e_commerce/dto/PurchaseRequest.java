package com.abs.e_commerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product id cannot be null")
        Long productId,
        @Positive(message = "quantity cannot be null")
        double quantity
        ) {
}
