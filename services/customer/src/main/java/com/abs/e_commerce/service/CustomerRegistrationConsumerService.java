package com.abs.e_commerce.service;

import com.abs.e_commerce.dto.CustomerRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRegistrationConsumerService {

    private final CustomerService customerService;

    @KafkaListener(topics = "customer-topic", groupId = "customerGroup")
    public void receiveCustomerRegistration(CustomerRegistration customerRegistration) {
        this.customerService.createCustomer(customerRegistration);
    }
}
