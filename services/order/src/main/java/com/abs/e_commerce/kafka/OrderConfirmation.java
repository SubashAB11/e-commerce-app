package com.abs.e_commerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.abs.e_commerce.customer.CustomerResponse;
import com.abs.e_commerce.order.PaymentMethod;
import com.abs.e_commerce.product.ProductPurchaseResponse;

public record OrderConfirmation(
    String orderRef,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<ProductPurchaseResponse> products
) {

}
