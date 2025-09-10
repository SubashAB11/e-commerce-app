package com.abs.e_commerce.service;

import java.util.List;
import java.util.stream.Collectors;

import com.abs.e_commerce.dto.ProductRequest;
import com.abs.e_commerce.dto.ProductResponse;
import com.abs.e_commerce.model.Product;
import com.abs.e_commerce.helper.ProductMapper;
import com.abs.e_commerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

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
