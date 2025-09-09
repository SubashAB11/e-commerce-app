package com.abs.e_commerce.controller;

import java.util.List;

import com.abs.e_commerce.dto.OrderLineResponse;
import com.abs.e_commerce.service.OrderLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;


    @GetMapping("/get/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findById(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(service.getOrder(orderId));
    }

}
