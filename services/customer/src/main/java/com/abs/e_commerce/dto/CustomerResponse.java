package com.abs.e_commerce.dto;


import com.abs.e_commerce.model.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
