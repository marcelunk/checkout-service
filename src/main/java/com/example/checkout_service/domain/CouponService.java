package com.example.checkout_service.domain;

import java.time.Year;

import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;

@Service
public class CouponService {

    private static final String PROMO = "Promo";

    private final ProductService productService;

    private final CustomerService customerService;

    public CouponService(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    public boolean isEligibleForDiscountInCurrentYear(@Nonnull String customerId) {
        Customer customer = this.customerService.getCustomer(customerId);
        double totalSales = customer.getOrders().stream()
                .filter(o -> o.getDate().year == Year.now().getValue())
                .mapToDouble(Order::getTotal)
                .sum();
        return totalSales >= 1000 ? true : false;
    }

    public boolean isEligibleForDiscount(@Nonnull Item item) {
        String productId = item.getProductId();
        Product product = this.productService.getProduct(productId);
        int quantity = this.productService.getQuantity(productId);
        return (PROMO.equals(product.getCategory()) && quantity >= 5);
    }

    public Double getReducedPrice(@Nonnull Product product) {
        return product.getPrice() * 0.9;
    }

}
