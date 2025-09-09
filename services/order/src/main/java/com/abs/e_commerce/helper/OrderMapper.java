package com.abs.e_commerce.helper;

import com.abs.e_commerce.dto.OrderRequest;
import com.abs.e_commerce.dto.OrderResponse;
import com.abs.e_commerce.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(order.getId(), order.getReference(), order.getTotalAmount(), order.getPaymentMethod(),
                order.getCustomerId());
    }

}
