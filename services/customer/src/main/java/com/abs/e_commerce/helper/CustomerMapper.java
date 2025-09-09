package com.abs.e_commerce.helper;

import com.abs.e_commerce.dto.CustomerRequest;
import com.abs.e_commerce.dto.CustomerResponse;
import com.abs.e_commerce.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(request == null) return null;
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress()
        );
    }

    public com.abs.e_commerce.proto.GetCustomerResponse fromCustomerGrpc(Customer customer) {
        return com.abs.e_commerce.proto.GetCustomerResponse.newBuilder()
                .setId(customer.getId())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setAddress(com.abs.e_commerce.proto.Address.newBuilder()
                        .setHouseNumber(customer.getAddress().getHouseNumber())
                        .setStreet(customer.getAddress().getStreet())
                        .setZipCode(customer.getAddress().getZipCode())
                        .build())
                .build();
    }
}
