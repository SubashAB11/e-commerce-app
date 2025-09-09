package com.abs.e_commerce.dto;

import java.math.BigDecimal;

public record PaymentConfirmation(
    String orderRef,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {

}
