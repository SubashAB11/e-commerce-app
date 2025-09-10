package com.abs.auth.service;

import com.abs.auth.dto.CustomerRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRegistrationProducerService {

    private final KafkaTemplate<String, CustomerRegistration> kafkaTemplate;

    public void sendCustomerRegistration(CustomerRegistration customerRegistration) {
        Message<CustomerRegistration> message = MessageBuilder
                .withPayload(customerRegistration)
                .setHeader(KafkaHeaders.TOPIC, "customer-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
