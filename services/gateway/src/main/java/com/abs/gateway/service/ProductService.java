package com.abs.gateway.service;

import com.abs.gateway.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("lb://PRODUCT-SERVICE").build();
    }

    public Mono<Product> getProductById(Long id) {
        return this.webClient.get()
                .uri("/api/v1/product/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
