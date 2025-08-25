package com.abs.e_commerce.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.abs.e_commerce.kafka.order.OrderConfirmation;
import com.abs.e_commerce.kafka.payment.PaymentConfirmation;
import com.abs.e_commerce.mail.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

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
