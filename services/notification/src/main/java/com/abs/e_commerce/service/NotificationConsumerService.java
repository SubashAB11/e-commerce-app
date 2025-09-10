package com.abs.e_commerce.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.abs.e_commerce.dto.OrderConfirmation;
import com.abs.e_commerce.dto.PaymentConfirmation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumerService {

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccesfulNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("consuming from payment topic");
        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sentPaymentSuccessful(paymentConfirmation.customerEmail(), customerName,
                paymentConfirmation.amount(), paymentConfirmation.orderRef());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccesfulNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("consuming from order topic");
        String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sentOrderConfirmation(orderConfirmation.customer().email(), customerName,
                orderConfirmation.totalAmount(), orderConfirmation.orderRef(), orderConfirmation.products());
    }
}
