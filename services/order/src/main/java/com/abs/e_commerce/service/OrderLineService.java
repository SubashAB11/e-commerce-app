package com.abs.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.abs.e_commerce.dto.OrderLineRequest;
import com.abs.e_commerce.dto.OrderLineResponse;
import com.abs.e_commerce.helper.OrderLineMapper;
import com.abs.e_commerce.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> getOrder(Long orderId) {
        return repository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());
    }

}
