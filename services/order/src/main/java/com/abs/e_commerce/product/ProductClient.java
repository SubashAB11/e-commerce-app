package com.abs.e_commerce.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    public List<ProductPurchaseResponse> purchaseProducts(
            @RequestBody List<PurchaseRequest> request);
}
