package com.abs.e_commerce.helper;

import com.abs.e_commerce.dto.OrderLineRequest;
import com.abs.e_commerce.dto.OrderLineResponse;
import com.abs.e_commerce.model.OrderLine;
import org.springframework.stereotype.Service;

import com.abs.e_commerce.model.Order;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
        .id(orderLineRequest.id())
        .quantity(orderLineRequest.quantity())
        .order(Order.builder().id(orderLineRequest.orderId()).build())
        .productId(orderLineRequest.productId())
        .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }

}
