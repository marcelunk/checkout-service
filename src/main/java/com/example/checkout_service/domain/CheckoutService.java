package com.example.checkout_service.domain;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    private final CouponService couponService;

    private final ProductService productService;

    private final CustomerService customerService;

    public CheckoutService(CouponService couponService, ProductService productService,
            CustomerService customerService) {
        this.couponService = couponService;
        this.productService = productService;
        this.customerService = customerService;
    }

    public CheckoutResult checkout(String customerId, List<Item> basket) {
        List<CheckoutItem> checkoutItems = checkoutBasket(customerId, basket);
        CheckoutResult result = new CheckoutResult(checkoutItems);
        result.checkout();
        Logger.getAnonymousLogger().info(String.format("### Checked out:\n %s", result));
        return result;
    }

    private List<CheckoutItem> checkoutBasket(String customerId, List<Item> basket) {
        return basket.stream()
                .<CheckoutItem>map(item -> checkoutItem(customerId, item))
                .toList();
    }

    private CheckoutItem checkoutItem(String customerId, Item item) {
        boolean customerIsEligibleForDiscount = this.couponService
                .isEligibleForDiscountInCurrentYear(customerId);
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
    }

    public boolean validate(Checkout checkout) {
        boolean hasCustomer = this.customerService.hasCustomer(checkout.getCustomerId());
        boolean hasProducts = checkout.getBasket().stream()
                .map(Item::getProductId)
                .allMatch(id -> this.productService.hasProduct(id));
        return hasCustomer && hasProducts;
    }

}
