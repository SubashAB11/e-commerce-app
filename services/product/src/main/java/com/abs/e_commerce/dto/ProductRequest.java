package com.abs.e_commerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    Long id,

    @NotNull(message = "product name is required")
    String name,

    @NotNull(message = "product description is required")
    String description,

    @Positive(message = "available quantity should be positive")
    double availableQuantity,
    
    @Positive(message = "price should be positive")
    BigDecimal price,

    @NotNull(message = "category cannot be null")
    Long categoryId
) {

}
