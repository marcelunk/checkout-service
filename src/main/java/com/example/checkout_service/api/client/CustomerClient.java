package com.example.checkout_service.api.client;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.checkout_service.api.dto.CustomerDto;

@Component
public class CustomerClient {

    private final DataProvider<CustomerDto> dataProvider;

    public CustomerClient(DataProvider<CustomerDto> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public List<CustomerDto> loadCustomers() {
        return this.dataProvider.loadData();
    }

}
