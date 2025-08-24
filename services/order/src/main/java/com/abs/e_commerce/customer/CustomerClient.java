package com.abs.e_commerce.customer;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/get/{customer-id}")
    Optional<CustomerResponse> getCustomerById(@PathVariable("customer-id") String customerId);
}
