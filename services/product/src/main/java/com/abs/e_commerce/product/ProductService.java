package com.abs.e_commerce.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abs.e_commerce.exception.ProductPurchaseException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Long createProduct(ProductRequest request) {
        Product product = repository.save(mapper.toProduct(request));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) throw new ProductPurchaseException("one or more products in the request not exists");
        var sortedRequest = request.stream().sorted(Comparator.comparingLong(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()) throw new ProductPurchaseException("quantity exceeds for the product " + product.getId());
            var newQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product));
        }
        return purchasedProducts;
    }

    public ProductResponse getProduct(Long productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("product not found"));
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

}
