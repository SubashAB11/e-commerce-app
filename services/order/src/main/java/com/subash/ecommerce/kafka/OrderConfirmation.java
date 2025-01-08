package com.subash.ecommerce.kafka;

import com.subash.ecommerce.customer.CustomerResponse;
import com.subash.ecommerce.order.PaymentMethod;
import com.subash.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
