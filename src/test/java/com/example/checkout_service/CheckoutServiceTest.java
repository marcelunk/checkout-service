package com.example.checkout_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.checkout_service.domain.Checkout;
import com.example.checkout_service.domain.CheckoutItem;
import com.example.checkout_service.domain.CheckoutResult;
import com.example.checkout_service.domain.CheckoutService;
import com.example.checkout_service.domain.CouponService;
import com.example.checkout_service.domain.CustomerService;
import com.example.checkout_service.domain.Item;
import com.example.checkout_service.domain.Product;
import com.example.checkout_service.domain.ProductService;

public class CheckoutServiceTest {

    private CouponService couponService;
    private ProductService productService;
    private CustomerService customerService;

    private CheckoutService checkoutService;

    @BeforeEach
    void setUp() {
        couponService = mock(CouponService.class);
        productService = mock(ProductService.class);
        customerService = mock(CustomerService.class);

        checkoutService = new CheckoutService(couponService, productService, customerService);
    }

    @Test
    void testCheckoutWithoutDiscount() {
        String customerId = "C1001";
        String productId = "P100";
        Item item = new Item(productId, 2);
        Product product = new Product(productId, "Test Product", "Standard", 10, 100.0);

        when(couponService.isEligibleForDiscountInCurrentYear(customerId)).thenReturn(true);
        when(couponService.isEligibleForDiscount(item)).thenReturn(false);
        when(couponService.getReducedPrice(product)).thenReturn(100.0);
        when(productService.getProduct(productId)).thenReturn(product);

        CheckoutResult result = checkoutService.checkout(customerId, List.of(item));

        assertEquals(1, result.getItems().size());
        CheckoutItem checkoutItem = result.getItems().get(0);
        assertEquals("Test Product", checkoutItem.getProductName());
        assertEquals(100.0, checkoutItem.getSinglePrice());
        assertEquals(200.0, checkoutItem.getOriginalPrice());
        assertEquals(200.0, checkoutItem.getNewPrice());
    }

    @Test
    void testCheckoutWithDiscount() {
        String customerId = "C1001";
        String productId = "P100";
        Item item = new Item(productId, 2);
        Product product = new Product(productId, "Test Product", "Promo", 10, 100.0);

        when(couponService.isEligibleForDiscountInCurrentYear(customerId)).thenReturn(true);
        when(couponService.isEligibleForDiscount(item)).thenReturn(true);
        when(couponService.getReducedPrice(product)).thenReturn(80.0);
        when(productService.getProduct(productId)).thenReturn(product);

        CheckoutResult result = checkoutService.checkout(customerId, List.of(item));

        assertEquals(1, result.getItems().size());
        CheckoutItem checkoutItem = result.getItems().get(0);
        assertEquals("Test Product", checkoutItem.getProductName());
        assertEquals(80.0, checkoutItem.getSinglePrice());
        assertEquals(200.0, checkoutItem.getOriginalPrice());
        assertEquals(160.0, checkoutItem.getNewPrice());
    }

    @Test
    void testValidateWithValidData() {
        String customerId = "C1001";
        Item item = new Item("P100", 1);
        Checkout checkout = new Checkout(customerId, List.of(item));

        when(customerService.hasCustomer(customerId)).thenReturn(true);
        when(productService.hasProduct("P100")).thenReturn(true);

        boolean isValid = checkoutService.validate(checkout);
        assertTrue(isValid);
    }

    @Test
    void testValidateInvalidCustomer() {
        String customerId = "invalid";
        Item item = new Item("P100", 1);
        Checkout checkout = new Checkout(customerId, List.of(item));

        when(customerService.hasCustomer(customerId)).thenReturn(false);
        when(productService.hasProduct("P100")).thenReturn(true);

        boolean isValid = checkoutService.validate(checkout);
        assertFalse(isValid);
    }

    @Test
    void testValidateInvalidProduct() {
        String customerId = "C1001";
        String productId = "invalid";
        Item item = new Item(productId, 1);
        Checkout checkout = new Checkout(customerId, List.of(item));

        when(customerService.hasCustomer(customerId)).thenReturn(true);
        when(productService.hasProduct(productId)).thenReturn(false);

        boolean isValid = checkoutService.validate(checkout);
        assertFalse(isValid);
    }

    @Test
    void testValidateEmptyBasket() {
        Checkout checkout = new Checkout("C1001", List.of());
        boolean isValid = checkoutService.validate(checkout);
        assertFalse(isValid);
    }
}
