package com.example.checkout_service.api.client;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.checkout_service.api.dto.ProductDto;

@Component
public class ProductClient {

    private final DataProvider<ProductDto> dataProvider;

    public ProductClient(DataProvider<ProductDto> productProvider) {
        this.dataProvider = productProvider;
    }

    public List<ProductDto> loadProducts() {
        return this.dataProvider.loadData();
    }

}
