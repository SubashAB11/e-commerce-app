package com.abs.e_commerce.notification;

import java.math.BigDecimal;

import com.abs.e_commerce.payment.PaymentMethod;

public record PaymentNotificationRequest(
    String orderRef,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {

}
