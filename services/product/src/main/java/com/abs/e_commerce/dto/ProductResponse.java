package com.abs.e_commerce.dto;

import java.math.BigDecimal;

public record ProductResponse(
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
