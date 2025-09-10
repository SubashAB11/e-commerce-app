package com.abs.e_commerce.dto;

import java.math.BigDecimal;

import com.abs.e_commerce.model.PaymentMethod;

public record PaymentNotificationRequest(
    String orderRef,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {

}
