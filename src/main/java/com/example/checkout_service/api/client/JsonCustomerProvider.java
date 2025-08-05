package com.example.checkout_service.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.checkout_service.api.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonCustomerProvider extends DataProvider<CustomerDto> {

    public JsonCustomerProvider(@Value("${customerclient.data.path:}") String dataPath, ObjectMapper objectMapper,
            ResourceLoader resourceLoader) {
        super(dataPath, objectMapper, resourceLoader, CustomerDto.class);
    }

}
