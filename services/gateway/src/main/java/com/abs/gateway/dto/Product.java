package com.abs.gateway.dto;

import java.math.BigDecimal;

public record Product(
        Long id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
