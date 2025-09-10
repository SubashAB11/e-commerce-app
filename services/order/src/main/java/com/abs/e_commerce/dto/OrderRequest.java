package com.abs.e_commerce.dto;

import java.math.BigDecimal;
import java.util.List;

import com.abs.e_commerce.model.PaymentMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
    Long id,
    String reference,
    @Positive(message = "order amount should be positive")
    BigDecimal amount,
    @NotNull(message = "payment method should be precise")
    PaymentMethod paymentMethod,
    @NotNull(message = "customer id cannot be null")
    @NotEmpty(message = "customer id cannot be null")
    @NotBlank(message = "customer id cannot be null")
    String customerId,
    @NotEmpty(message = "user must purchase atleast one product")
    List<PurchaseRequest> products
) {

}
