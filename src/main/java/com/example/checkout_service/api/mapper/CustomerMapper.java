package com.example.checkout_service.api.mapper;

import com.example.checkout_service.api.dto.CustomerDto;
import com.example.checkout_service.domain.Customer;
import com.example.checkout_service.domain.Order;

public class CustomerMapper {

    public static Customer toDomain(CustomerDto dto) {
        return new Customer(dto.customerId(),
                dto.orders().stream().map(o -> new Order(o.orderId(), o.date(), o.total())).toList());
    }

}
