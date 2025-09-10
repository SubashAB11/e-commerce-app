package com.abs.e_commerce.dto;

import com.abs.e_commerce.model.Address;
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
