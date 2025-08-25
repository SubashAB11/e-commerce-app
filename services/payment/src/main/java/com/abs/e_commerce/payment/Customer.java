package com.abs.e_commerce.payment;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record Customer(
    String id,
    @NotNull(message = "customer first name is required")
    String firstName,
    @NotNull(message = "customer last name is required")
    String lastName,
    @NotNull(message = "customer email is required")
    @Email(message = "cutomer email invalid")
    String email
) {

}
