package com.abs.e_commerce.dto;

import java.math.BigDecimal;
import java.util.List;

import com.abs.e_commerce.model.PaymentMethod;

public record OrderConfirmation(
    String orderRef,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<ProductPurchaseResponse> products
) {

}
