package com.abs.e_commerce.customer;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/api/v1/customer/get/{customer-id}")
    Optional<CustomerResponse> getCustomerById(@PathVariable("customer-id") String customerId);
}
