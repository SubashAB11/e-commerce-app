package com.abs.e_commerce.client;

import com.abs.e_commerce.service.GrpcChannelFactory;
import com.abs.e_commerce.proto.PaymentRequest;
import com.abs.e_commerce.proto.PaymentResponse;
import com.abs.e_commerce.proto.PaymentServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentClient {


    private final PaymentServiceGrpc.PaymentServiceBlockingStub blockingStub;

    public PaymentClient(GrpcChannelFactory channelFactory, @Value("${application.config.service.name.payment}") String serviceName) {
        ManagedChannel managedChannel = channelFactory.createChannel(serviceName);
        blockingStub = PaymentServiceGrpc.newBlockingStub(managedChannel);
    }

    public Long createPayment(PaymentRequest request) {
        PaymentResponse payment = blockingStub.createPayment(request);
        return payment.getId();
    }
}
