package com.abs.e_commerce.dto;

import com.abs.e_commerce.model.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
    Long id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
) {

}
