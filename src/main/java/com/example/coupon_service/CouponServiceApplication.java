package com.example.coupon_service;

import java.util.Collection;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.coupon_service.domain.Basket;
import com.example.coupon_service.domain.CheckoutResult;
import com.example.coupon_service.domain.CheckoutService;
import com.example.coupon_service.domain.Customer;
import com.example.coupon_service.domain.CustomerService;
import com.example.coupon_service.domain.Item;
import com.example.coupon_service.domain.Product;
import com.example.coupon_service.domain.ProductService;

@SpringBootApplication
public class CouponServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner testProductClient(ProductService productService) {
        return args -> {
            Collection<Product> products = productService.getProducts();
            System.out.println("Products (Startup Test):");
            products.forEach(product -> System.out.println(product));
        };
    }

    @Bean
    public CommandLineRunner testCustomerClient(CustomerService customerService) {
        return args -> {
            Set<Customer> customers = customerService.getCustomers();
            System.out.println("Customers (Startup Test):");
            customers.forEach(c -> System.out.println(c));
        };
    }

    @Bean
    public CommandLineRunner testCheckout(CheckoutService checkoutService, CustomerService customerService) {
        return args -> {
            Basket basket = new Basket();
            basket.addToBasket(new Item("P100", 1));
            basket.addToBasket(new Item("P101", 1));
            basket.addToBasket(new Item("P200", 2));

            Customer customer = customerService.getCustomer("C1001");

            CheckoutResult result = checkoutService.checkout(customer, basket);

            System.out.println("Checkout (Startup Test):");
            System.out.println(result);
        };
    }

}
