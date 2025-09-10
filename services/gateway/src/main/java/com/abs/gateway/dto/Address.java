package com.abs.gateway.dto;

public record Address(
        String street,
        String houseNumber,
        String zipCode
) {
}
