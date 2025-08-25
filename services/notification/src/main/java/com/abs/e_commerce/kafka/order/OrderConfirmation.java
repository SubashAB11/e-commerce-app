package com.abs.e_commerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.abs.e_commerce.kafka.payment.PaymentMethod;

public record OrderConfirmation(
    String orderRef,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    Customer customer,
    List<Product> products
) {

}
