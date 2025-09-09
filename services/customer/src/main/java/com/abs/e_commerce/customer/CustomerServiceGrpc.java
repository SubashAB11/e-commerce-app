package com.abs.e_commerce.customer;

import com.abs.e_commerce.exception.CustomerNotFoundException;
import com.abs.e_commerce.proto.GetCustomerRequest;
import com.abs.e_commerce.proto.GetCustomerResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CustomerServiceGrpc extends com.abs.e_commerce.proto.CustomerServiceGrpc.CustomerServiceImplBase{

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public void getCustomerById(GetCustomerRequest request, StreamObserver<GetCustomerResponse> responseObserver) {
        var customer = repository.findById(request.getId())
                .map(mapper::fromCustomerGrpc)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));

        responseObserver.onNext(customer);
        responseObserver.onCompleted();
    }
}
