package com.abs.e_commerce.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcChannelFactory {

    private final DiscoveryClient discoveryClient;

    public ManagedChannel createChannel(String serviceName) {
        ServiceInstance instance = discoveryClient.getInstances(serviceName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No available service instances"));

        String host = instance.getHost();
        int port = Integer.parseInt(instance.getMetadata().get("gRPC"));

        return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    }


}
