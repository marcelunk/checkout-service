package com.example.coupon_service.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.coupon_service.api.client.CustomerClient;
import com.example.coupon_service.api.mapper.CustomerMapper;

@Service
public class CustomerService {

    private final CustomerClient client;

    private List<Customer> customers;

    public CustomerService(CustomerClient client) {
        this.client = client;
    }

    public List<Customer> getCustomers() {
        if (customers == null) {
            this.customers = this.client.loadCustomers().stream().map(CustomerMapper::toDomain).toList();
        }

        return this.customers;
    }

}
