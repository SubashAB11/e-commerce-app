package com.abs.e_commerce.kafka.order;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email) {

}
