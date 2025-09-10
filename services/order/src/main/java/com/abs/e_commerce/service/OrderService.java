package com.abs.e_commerce.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.abs.e_commerce.dto.CustomerResponse;
import com.abs.e_commerce.dto.OrderRequest;
import com.abs.e_commerce.dto.OrderResponse;
import com.abs.e_commerce.dto.ProductPurchaseResponse;
import com.abs.e_commerce.helper.OrderMapper;
import com.abs.e_commerce.repository.OrderRepository;
import com.abs.e_commerce.proto.Customer;
import com.abs.e_commerce.proto.GetCustomerResponse;
import com.abs.e_commerce.proto.PaymentMethod;
import com.abs.e_commerce.proto.PaymentRequest;
import com.google.type.Money;
import org.springframework.stereotype.Service;

import com.abs.e_commerce.client.CustomerClient;
import com.abs.e_commerce.dto.OrderConfirmation;
import com.abs.e_commerce.dto.OrderLineRequest;
import com.abs.e_commerce.client.PaymentClient;
import com.abs.e_commerce.client.ProductClient;
import com.abs.e_commerce.dto.PurchaseRequest;

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
    private final OrderProducerService orderProducerService;
    private final PaymentClient paymentClient;

    public Long createOrder(OrderRequest request) {

        // check the customer
        GetCustomerResponse getCustomerResponse = customerClient.fetchCustomer(request.customerId());

        // purchase the product from product service
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        // persist the order
        var order = repository.save(mapper.toOrder(request));

        // persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }

        // start payment process
        paymentClient.createPayment(PaymentRequest
                .newBuilder()
                .setAmount(Money.newBuilder().setCurrencyCode("INR").setUnits(request.amount().longValue()).build())
                .setPaymentMethod(PaymentMethod.valueOf(request.paymentMethod().name()))
                .setOrderId(order.getId())
                .setOrderRef(request.reference())
                .setCustomer(Customer.newBuilder().build())
                .build());

        // send the order confirmation using notification service
        List<ProductPurchaseResponse> products = purchasedProducts.stream().map(product ->
                new ProductPurchaseResponse(product.getProductId(), product.getName(), product.getDescription(), product.getQuantity(), new BigDecimal(product.getPrice().getUnits()))).toList();
        orderProducerService.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        new CustomerResponse(getCustomerResponse.getId(), getCustomerResponse.getFirstName(), getCustomerResponse.getLastName(), getCustomerResponse.getEmail()),
                        products));

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
