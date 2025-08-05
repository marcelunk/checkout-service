package com.example.coupon_service.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.coupon_service.api.client.ProductClient;
import com.example.coupon_service.api.dto.ProductDto;
import com.example.coupon_service.api.mapper.ProductMapper;

@Service
public class ProductService {

    private final ProductClient client;

    private Map<String, Product> productsById;

    public ProductService(ProductClient client) {
        this.client = client;
    }

    public Collection<Product> getProducts() {
        if (productsById == null) {
            this.loadProducts();
        }

        return this.productsById.values();
    }

    public Collection<String> getProductIds() {
        if (productsById == null) {
            this.loadProducts();
        }

        return this.productsById.keySet();
    }

    public Product getProduct(String productId) {
        for (Product product : this.getProducts()) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

    private void loadProducts() {
        List<ProductDto> productDtos = this.client.loadProducts();
        this.productsById = productDtos.stream()
                .<Product>map(ProductMapper::toDomain)
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));
    }

    public int getQuantity(String productId) {
        if (this.productsById.containsKey(productId)) {
            return this.productsById.get(productId).getStock();
        }

        return -1;
    }

    public boolean hasProduct(String productId) {
        return this.getProductIds().contains(productId);
    }

}
