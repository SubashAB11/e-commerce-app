package com.abs.e_commerce.payment;

import org.springframework.stereotype.Service;

import com.abs.e_commerce.notification.NotificationProducer;
import com.abs.e_commerce.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Long createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        // send notification to notification service through kafka
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(request.orderRef(), request.amount(), request.paymentMethod(),
                        request.customer().firstName(), request.customer().lastName(), request.customer().email()));

        return payment.getId();
    }

}
