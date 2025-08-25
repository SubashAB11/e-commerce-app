package com.abs.e_commerce.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, PaymentNotificationRequest> template;

    public void sendNotification(PaymentNotificationRequest request){
        log.info("sending notification to notification from payment");
        Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(request).setHeader(KafkaHeaders.TOPIC, "payment-topic").build();
        template.send(message);
    }
}
