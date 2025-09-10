package com.abs.gateway.service;

import com.abs.gateway.dto.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final WebClient webClient;

    public CustomerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("lb://CUSTOMER-SERVICE").build();
    }

    public Mono<Customer> getCustomerById(String id) {
        return this.webClient.get()
                .uri("/api/v1/customer/{id}", id)
                .retrieve()
                .bodyToMono(Customer.class);
    }
}
