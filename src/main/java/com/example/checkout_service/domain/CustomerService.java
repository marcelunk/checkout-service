package com.example.checkout_service.domain;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.checkout_service.api.client.CustomerClient;
import com.example.checkout_service.api.mapper.CustomerMapper;

@Service
public class CustomerService {

    private final CustomerClient client;

    private Set<Customer> customers;

    public CustomerService(CustomerClient client) {
        this.client = client;
    }

    public boolean hasCustomer(String customerId) {
        return this.getCustomers().stream()
                .map(Customer::getCustomerId)
                .anyMatch(id -> id.equals(customerId));
    }

    public Set<Customer> getCustomers() {
        if (customers == null) {
            this.loadCustomers();
        }

        return this.customers;
    }

    public Customer getCustomer(String customerId) {
        for (Customer customer : this.getCustomers()) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }

        return null;
    }

    private void loadCustomers() {
        this.customers = this.client.loadCustomers().stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toSet());
    }

}
