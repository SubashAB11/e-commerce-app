package com.abs.gateway.controller;

import com.abs.gateway.dto.Product;
import com.abs.gateway.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public Mono<Product> product(@Argument("id") Long id) {
        return this.productService.getProductById(id);
    }
}
