package com.example.coupon_service.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    private final CouponService couponService;

    private final ProductService productService;

    public CheckoutService(CouponService couponService, ProductService productService) {
        this.couponService = couponService;
        this.productService = productService;
    }

    public CheckoutResult checkout(Customer customer, Basket basket) {
        List<CheckoutItem> checkoutItems = checkoutBasket(customer, basket);
        CheckoutResult result = new CheckoutResult(checkoutItems);
        result.checkout();
        return result;
    }

    private List<CheckoutItem> checkoutBasket(Customer customer, Basket basket) {
        return basket.getItems().stream()
                .<CheckoutItem>map(item -> {
                    boolean customerIsEligibleForDiscount = this.couponService
                            .isEligibleForDiscountInCurrentYear(customer);
                    boolean itemIsEligibleForDiscount = false;
                    if (customerIsEligibleForDiscount) {
                        itemIsEligibleForDiscount = this.couponService.isEligibleForDiscount(item);
                    }

                    Product product = productService.getProduct(item.getProductId());
                    Double originalPrice = product.getPrice();
                    Double newPrice = originalPrice;
                    if (itemIsEligibleForDiscount) {
                        newPrice = this.couponService.getReducedPrice(product);
                    }

                    Integer quantity = item.getQuantity();
                    Double originalSum = originalPrice * quantity;
                    Double newSum = newPrice * quantity;
                    String description = String.format(
                            "Customer and Item are eligible for discount: %b", itemIsEligibleForDiscount);

                    return new CheckoutItem(product.getName(), newPrice, quantity, originalSum, newSum, description);
                })
                .toList();
    }

}
