package com.abs.e_commerce.client;

import java.util.List;

import com.abs.e_commerce.dto.PurchaseRequest;
import com.abs.e_commerce.service.GrpcChannelFactory;
import com.abs.e_commerce.proto.*;
import com.abs.e_commerce.proto.ProductPurchaseResponse;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductClient {

    private final ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    public ProductClient(GrpcChannelFactory grpcChannelFactory, @Value("${application.config.service.name.product}") String serviceName) {
        ManagedChannel channel = grpcChannelFactory.createChannel(serviceName);
        blockingStub = ProductServiceGrpc.newBlockingStub(channel);
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<PurchaseRequest> requests) {
        List<ProductPurchaseRequest> products = requests.stream().map(req -> ProductPurchaseRequest.newBuilder()
                .setProductId(req.productId())
                .setQuantity(req.quantity())
                .build()).toList();
        ProductPurchaseResponseList responseList = blockingStub.purchaseProducts(ProductPurchaseRequestList.newBuilder().addAllRequests(products).build());
        return responseList.getResponsesList();
    }
}
