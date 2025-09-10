package com.abs.auth.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

public record Address(
        String street,
        String houseNumber,
        String zipCode
) {
}
