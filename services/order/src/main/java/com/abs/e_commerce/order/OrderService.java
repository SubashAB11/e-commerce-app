package com.abs.e_commerce.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.abs.e_commerce.customer.CustomerResponse;
import com.abs.e_commerce.product.ProductPurchaseResponse;
import com.abs.e_commerce.proto.Customer;
import com.abs.e_commerce.proto.GetCustomerResponse;
import com.abs.e_commerce.proto.PaymentMethod;
import com.abs.e_commerce.proto.PaymentRequest;
import com.google.type.Money;
import org.springframework.stereotype.Service;

import com.abs.e_commerce.customer.CustomerClient;
import com.abs.e_commerce.exception.BusinessException;
import com.abs.e_commerce.kafka.OrderConfirmation;
import com.abs.e_commerce.kafka.OrderProducer;
import com.abs.e_commerce.orderLine.OrderLineRequest;
import com.abs.e_commerce.orderLine.OrderLineService;
import com.abs.e_commerce.payment.PaymentClient;
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
    private final PaymentClient paymentClient;

    public Long createOrder(OrderRequest request) {

        // check the customer
        GetCustomerResponse getCustomerResponse = customerClient.fetchCustomer(request.customerId());

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
        paymentClient.createPayment(PaymentRequest
                .newBuilder()
                .setAmount(Money.newBuilder().setCurrencyCode("INR").setUnits(request.amount().longValue()).build())
                .setPaymentMethod(PaymentMethod.valueOf(request.paymentMethod().name()))
                .setOrderId(order.getId())
                .setOrderRef(request.reference())
                .setCustomer(Customer.newBuilder().build())
                .build());

        // send the order confirmation using notification service
        List<ProductPurchaseResponse> products = puchasedProducts.stream().map(product ->
                new ProductPurchaseResponse(product.getProductId(), product.getName(), product.getDescription(), product.getQuantity(), new BigDecimal(product.getPrice().getUnits()))).toList();
        orderProducer.sendOrderConfirmation(
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
