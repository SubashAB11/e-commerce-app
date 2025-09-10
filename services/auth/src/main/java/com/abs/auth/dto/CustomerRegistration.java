package com.abs.auth.dto;

public record CustomerRegistration(
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
