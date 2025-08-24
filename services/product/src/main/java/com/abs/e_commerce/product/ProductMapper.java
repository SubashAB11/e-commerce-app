package com.abs.e_commerce.product;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(Category.builder().id(request.categoryId()).build())
                .build();

    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(),
                product.getAvailableQuantity(), product.getPrice(), product.getCategory().getId(),
                product.getCategory().getName(), product.getCategory().getDescription());
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product) {
        return new ProductPurchaseResponse(product.getId(), product.getName(), product.getDescription(),
                product.getAvailableQuantity(), product.getPrice());
    }

}
