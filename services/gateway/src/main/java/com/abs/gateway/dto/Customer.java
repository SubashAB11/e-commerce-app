package com.abs.gateway.dto;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
