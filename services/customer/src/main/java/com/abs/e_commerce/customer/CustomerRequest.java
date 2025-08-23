package com.abs.e_commerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,

        @NotNull(message = "firstname is required")
        String firstName,

        @NotNull(message = "lastname is required")
        String lastName,

        @Email(message = "email is not valid")
        @NotNull(message = "email is required")
        String email,
        Address address
) {
}
