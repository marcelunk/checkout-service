package com.example.checkout_service.api.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DataProvider<D> implements Loadable<D> {

    private final String dataPath;

    private final ResourceLoader resourceLoader;

    private final ObjectMapper objectMapper;

    private final Class<D> type;

    public DataProvider(String dataPath, ObjectMapper objectMapper, ResourceLoader resourceLoader, Class<D> type) {
        this.dataPath = dataPath;
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
        this.type = type;
    }

    public List<D> loadData() {
        try {
            Resource resource = this.resourceLoader.getResource(dataPath);
            InputStream inputStream = resource.getInputStream();

            JavaType listType = objectMapper
                    .getTypeFactory()
                    .constructCollectionType(List.class, type);

            return objectMapper.readValue(inputStream, listType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data", e);
        }
    }

}