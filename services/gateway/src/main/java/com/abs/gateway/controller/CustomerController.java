package com.abs.gateway.controller;

import com.abs.gateway.dto.Customer;
import com.abs.gateway.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping
    public Mono<Customer> customer(@Argument("id") String id) {
        return customerService.getCustomerById(id);
    }
}
