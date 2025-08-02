package com.example.coupon_service.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.coupon_service.api.client.ProductClient;
import com.example.coupon_service.api.dto.ProductDto;
import com.example.coupon_service.api.mapper.ProductMapper;

@Service
public class ProductService {

    private final ProductClient client;

    private List<Product> products;

    public ProductService(ProductClient client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        if (products == null) {
            List<ProductDto> productDtos = this.client.loadProducts();
            this.products = productDtos.stream().map(ProductMapper::toDomain).toList();
        }

        return this.products;
    }

}
