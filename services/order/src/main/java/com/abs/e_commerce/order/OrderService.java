package com.abs.e_commerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abs.e_commerce.customer.CustomerClient;
import com.abs.e_commerce.exception.BusinessException;
import com.abs.e_commerce.kafka.OrderConfirmation;
import com.abs.e_commerce.kafka.OrderProducer;
import com.abs.e_commerce.orderLine.OrderLineRequest;
import com.abs.e_commerce.orderLine.OrderLineService;
import com.abs.e_commerce.product.ProductClient;
import com.abs.e_commerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Long createOrder(OrderRequest request) {

        // check the customer
        var customer = customerClient.getCustomerById(request.customerId()).orElseThrow(
                () -> new BusinessException("customer with the id " + request.customerId() + " is not found"));

        // purchase the product from product service
        var puchasedProducts = this.productClient.purchaseProducts(request.products());

        // persist the order
        var order = repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        // start payment process

        // send the order confirmation using notification service
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        puchasedProducts));

        return order.getId();
    }

    public List<OrderResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrder(Long orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("no order found for that order ID"));
    }

}
