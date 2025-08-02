package com.example.coupon_service;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.coupon_service.api.dto.CustomerDto;
import com.example.coupon_service.api.dto.ProductDto;
import com.example.coupon_service.domain.CustomerService;
import com.example.coupon_service.domain.ProductService;

@SpringBootApplication
public class CouponServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner testShopClient(ProductService productService) {
        return args -> {
            List<ProductDto> products = productService.getProducts();
            System.out.println("Products (Startup Test):");
            products.forEach(product -> System.out.println(product.name()));
        };
    }

    @Bean
    public CommandLineRunner testCustomerClient(CustomerService customerService) {
        return args -> {
            List<CustomerDto> customers = customerService.getCustomers();
            System.out.println("Customers (Startup Test):");
            customers.forEach(c -> System.out.println(c.customerId()));
        };
    }

}
