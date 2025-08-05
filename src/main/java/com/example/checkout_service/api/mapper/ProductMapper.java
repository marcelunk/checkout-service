package com.example.checkout_service.api.mapper;

import com.example.checkout_service.api.dto.ProductDto;
import com.example.checkout_service.domain.Product;

public class ProductMapper {

    public static Product toDomain(ProductDto dto) {
        return new Product(
                dto.productId(),
                dto.name(),
                dto.category(),
                dto.stock(),
                dto.price());
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getCategory(),
                product.getStock(),
                product.getPrice());
    }
}
