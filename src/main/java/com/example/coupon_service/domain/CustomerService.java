package com.example.coupon_service.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.coupon_service.api.client.CustomerClient;
import com.example.coupon_service.api.dto.CustomerDto;

@Service
public class CustomerService {

    private final CustomerClient client;

    private List<CustomerDto> customers;

    public CustomerService(CustomerClient client) {
        this.client = client;
    }

    public List<CustomerDto> getCustomers() {
        if (customers == null) {
            this.customers = this.client.loadCustomers();
        }

        return this.customers;
    }

}
