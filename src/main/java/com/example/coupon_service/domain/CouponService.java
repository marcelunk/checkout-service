package com.example.coupon_service.domain;

import java.time.Year;

import org.springframework.stereotype.Service;

import jakarta.annotation.Nonnull;

@Service
public class CouponService {

    private static final String PROMO = "Promo";

    private final ProductService productService;

    public CouponService(ProductService productService) {
        this.productService = productService;
    }

    public boolean isEligibleForDiscountInCurrentYear(@Nonnull Customer customer) {
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
