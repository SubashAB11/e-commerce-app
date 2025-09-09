package com.abs.e_commerce.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product id is required") 
        Long productId,
        @NotNull(message = "quantity is required") 
        double quantity
        ) {

}
