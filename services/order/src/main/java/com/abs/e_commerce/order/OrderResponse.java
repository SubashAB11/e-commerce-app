package com.abs.e_commerce.order;

import java.math.BigDecimal;

public record OrderResponse(
    Long id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
) {

}
