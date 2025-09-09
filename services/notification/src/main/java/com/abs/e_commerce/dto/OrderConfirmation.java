package com.abs.e_commerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderRef,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    Customer customer,
    List<Product> products
) {

}
