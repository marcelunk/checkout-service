package com.example.coupon_service.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.coupon_service.api.client.ProductClient;
import com.example.coupon_service.api.dto.ProductDto;

@Service
public class ProductService {

    private final ProductClient client;

    private List<ProductDto> products;

    public ProductService(ProductClient client) {
        this.client = client;
    }

    public List<ProductDto> getProducts() {
        if (products == null) {
            this.products = this.client.loadProducts();
        }

        return this.products;
    }

}
