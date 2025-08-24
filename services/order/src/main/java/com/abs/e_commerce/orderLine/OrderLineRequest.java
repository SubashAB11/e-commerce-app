package com.abs.e_commerce.orderLine;

public record OrderLineRequest(
    Long id,
    Long orderId,
    Long productId,
    double quantity
) {

}
