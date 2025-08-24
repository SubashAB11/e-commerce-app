package com.abs.e_commerce.orderLine;

import com.abs.e_commerce.order.Order;

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
