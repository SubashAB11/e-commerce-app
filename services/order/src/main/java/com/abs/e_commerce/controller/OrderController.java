package com.abs.e_commerce.controller;

import java.util.List;

import com.abs.e_commerce.dto.OrderRequest;
import com.abs.e_commerce.dto.OrderResponse;
import com.abs.e_commerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;


    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(service.createOrder(request));
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(service.getOrder(orderId));
    }


}
