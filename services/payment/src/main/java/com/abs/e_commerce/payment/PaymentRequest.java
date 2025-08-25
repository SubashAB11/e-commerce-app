package com.abs.e_commerce.payment;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequest(
    Long id,
    @Positive(message = "payment amount cannot be negative")
    @NotNull(message = "payment amount has to be present")
    BigDecimal amount,
    @NotNull(message = "payment method has to be present")
    PaymentMethod paymentMethod,
    @NotNull(message = "order id has to be present")
    Long orderId,
    @NotNull(message = "order ref has to be present")
    String orderRef,
    Customer customer
) {

}
