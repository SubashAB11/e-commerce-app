package com.abs.auth.helper;

import com.abs.auth.dto.RegistrationRequest;
import com.abs.auth.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(RegistrationRequest request) {
        return Customer.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}
