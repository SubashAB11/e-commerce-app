package com.abs.e_commerce.dto;


public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
