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

    public ProductResponse getProduct(Long productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("product not found"));
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }

}
