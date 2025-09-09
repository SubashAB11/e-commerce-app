package com.abs.e_commerce.service;

import com.abs.e_commerce.exception.ProductPurchaseException;
import com.abs.e_commerce.helper.ProductMapper;
import com.abs.e_commerce.repository.ProductRepository;
import com.abs.e_commerce.proto.ProductPurchaseRequestList;
import com.abs.e_commerce.proto.ProductPurchaseResponse;
import com.abs.e_commerce.proto.ProductPurchaseResponseList;
import com.abs.e_commerce.proto.ProductServiceGrpc.ProductServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class ProductServiceGrpc extends ProductServiceImplBase {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public void purchaseProducts(ProductPurchaseRequestList request, StreamObserver<ProductPurchaseResponseList> responseObserver) {
        List<ProductPurchaseResponse> productPurchaseResponses = purchaseProducts(request.getRequestsList());
        var response = com.abs.e_commerce.proto.ProductPurchaseResponseList.newBuilder().addAllResponses(productPurchaseResponses).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public List<com.abs.e_commerce.proto.ProductPurchaseResponse> purchaseProducts(List<com.abs.e_commerce.proto.ProductPurchaseRequest> request) {
        var productIds = request.stream().map(com.abs.e_commerce.proto.ProductPurchaseRequest::getProductId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) throw new ProductPurchaseException("one or more products in the request not exists");
        var sortedRequest = request.stream().sorted(Comparator.comparingLong(com.abs.e_commerce.proto.ProductPurchaseRequest::getProductId)).toList();
        var purchasedProducts = new ArrayList<com.abs.e_commerce.proto.ProductPurchaseResponse>();
        for(int i=0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.getQuantity()) throw new ProductPurchaseException("quantity exceeds for the product " + product.getId());
            var newQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product));
        }
        return purchasedProducts;
    }
}
