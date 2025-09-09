package com.abs.e_commerce.dto;

public record OrderLineRequest(
    Long id,
    Long orderId,
    Long productId,
    double quantity
) {

}
