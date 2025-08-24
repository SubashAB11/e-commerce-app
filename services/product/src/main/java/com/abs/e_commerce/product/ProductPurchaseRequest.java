package com.abs.e_commerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product id is required") 
        Long productId,
        @NotNull(message = "quantity is required") 
        double quantity
        ) {

}
