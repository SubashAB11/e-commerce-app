package com.abs.e_commerce.client;


import com.abs.e_commerce.service.GrpcChannelFactory;
import com.abs.e_commerce.proto.CustomerServiceGrpc;
import com.abs.e_commerce.proto.GetCustomerRequest;
import com.abs.e_commerce.proto.GetCustomerResponse;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerClient {

    private final CustomerServiceGrpc.CustomerServiceBlockingStub blockingStub;

    public CustomerClient(GrpcChannelFactory grpcChannelFactory,
                          @Value("${application.config.service.name.customer}") String serviceName) {
        ManagedChannel channel = grpcChannelFactory.createChannel(serviceName);
        this.blockingStub = CustomerServiceGrpc.newBlockingStub(channel);
    }

    public GetCustomerResponse fetchCustomer(String id) {
        GetCustomerRequest req = GetCustomerRequest.newBuilder()
                .setId(id)
                .build();

        return blockingStub.getCustomerById(req);
    }
}
