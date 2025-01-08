package com.subash.ecommerce.payment;

import com.subash.ecommerce.customer.CustomerResponse;
import com.subash.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {
}
