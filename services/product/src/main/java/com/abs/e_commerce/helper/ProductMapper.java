package com.abs.e_commerce.helper;

import com.abs.e_commerce.dto.ProductRequest;
import com.abs.e_commerce.dto.ProductResponse;
import com.abs.e_commerce.model.Category;
import com.abs.e_commerce.model.Product;
import com.abs.e_commerce.proto.ProductPurchaseResponse;
import com.google.type.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    public com.abs.e_commerce.proto.ProductPurchaseResponse toProductPurchaseResponse(Product product) {
        BigDecimal amount = product.getPrice();
        String currencyCode = "INR";
        long units = amount.longValue();
        int nanos = amount.subtract(new BigDecimal(units))
                .movePointRight(9)
                .intValue();
        return ProductPurchaseResponse.newBuilder()
                .setProductId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setQuantity(product.getAvailableQuantity())
                .setPrice(Money.newBuilder().setCurrencyCode(currencyCode).setUnits(units).setNanos(nanos).build())
                .build();
    }

}
