package com.abs.e_commerce.customer;

import com.abs.e_commerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer c = repository.save(mapper.toCustomer(request));
        return c.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException("Cannot update customer"));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(!request.firstName().isBlank()){
            customer.setFirstName(request.firstName());
        }
        if(!request.lastName().isBlank()){
            customer.setLastName(request.lastName());
        }
        if(!request.email().isBlank()){
            customer.setEmail(request.email());
        }
        if(request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = repository.findAll();
        return customers.stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public CustomerResponse getCustomer(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
