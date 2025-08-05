package com.example.checkout_service.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.checkout_service.api.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonProductProvider extends DataProvider<ProductDto> {

    public JsonProductProvider(@Value("${shopclient.data.path:}") String dataPath, ObjectMapper objectMapper,
            ResourceLoader resourceLoader) {
        super(dataPath, objectMapper, resourceLoader, ProductDto.class);
    }

}
