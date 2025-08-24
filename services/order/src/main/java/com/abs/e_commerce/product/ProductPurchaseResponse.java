package com.abs.e_commerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
    Long id,
    String name,
    String description,
    double quantity,
    BigDecimal price
) {

}
