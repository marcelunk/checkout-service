package com.example.checkout_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.checkout_service.domain.CouponService;
import com.example.checkout_service.domain.Customer;
import com.example.checkout_service.domain.CustomerService;
import com.example.checkout_service.domain.Item;
import com.example.checkout_service.domain.Order;
import com.example.checkout_service.domain.Product;
import com.example.checkout_service.domain.ProductService;

public class CouponServiceTest {

    private ProductService productService;
    private CustomerService customerService;

    private CouponService couponService;

    @BeforeEach
    void setUp() {
        customerService = mock(CustomerService.class);
        productService = mock(ProductService.class);

        couponService = new CouponService(productService, customerService);
    }

    @Test
    void customerIsEligibleForDiscountInCurrentYear() {
        String customerId = "C1001";
        var orders = List.of(
                new Order("010001", "2025-01-15", 200.00),
                new Order("010002", "2025-03-10", 500.50),
                new Order("010003", "2025-06-21", 500.00));
        Customer customer = new Customer(customerId, orders);

        when(customerService.getCustomer(customerId)).thenReturn(customer);

        assertTrue(couponService.isEligibleForDiscountInCurrentYear(customerId));
    }

    @Test
    void customerIsNotEligibleForDiscountInCurrentYear() {
        String customerId = "C1002";
        var orders = List.of(
                new Order("010004", "2025-02-12", 300.00),
                new Order("010005", "2025-07-01", 550.00));
        Customer customer = new Customer(customerId, orders);

        when(customerService.getCustomer(customerId)).thenReturn(customer);

        assertFalse(couponService.isEligibleForDiscountInCurrentYear(customerId));
    }

    @Test
    void itemIsEligibleForDiscount() {
        String productId = "P100";
        Item item = new Item(productId, 1);
        Product product = new Product("P100", "Massagegerät A", "Promo", 12, 199.99);

        when(productService.getStock(productId)).thenReturn(6);
        when(productService.getProduct(productId)).thenReturn(product);

        assertTrue(couponService.isEligibleForDiscount(item));
    }

    @Test
    void itemIsNotEligibleForDiscount() {
        String productId = "P100";
        Item item = new Item(productId, 1);
        Product product = new Product("P100", "Massagegerät A", "Standard", 12, 199.99);

        when(productService.getStock(productId)).thenReturn(4);
        when(productService.getProduct(productId)).thenReturn(product);

        assertFalse(couponService.isEligibleForDiscount(item));
    }

    @Test
    void getReducedPrice() {
        Product product = new Product("P100", "Massagegerät A", "Standard", 12, 10.0);
        assertEquals(9.0, couponService.getReducedPrice(product));
    }

}
