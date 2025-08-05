package com.example.checkout_service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.checkout_service.api.dto.CheckoutRequestDto;
import com.example.checkout_service.api.dto.CheckoutResponseDto;
import com.example.checkout_service.domain.Checkout;
import com.example.checkout_service.domain.CheckoutResult;
import com.example.checkout_service.domain.CheckoutService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/checkout")
public class CheckoutRestController {

    private CheckoutService checkoutService;

    public CheckoutRestController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping()
    public ResponseEntity<CheckoutResponseDto> createCheckoutResult(@Valid @RequestBody CheckoutRequestDto request) {

        // Map from API to Domain
        Checkout checkout = request.toCheckout();

        if (!this.checkoutService.validate(checkout)) {
            return ResponseEntity.badRequest().body(null);
        }

        CheckoutResult result = this.checkoutService.checkout(checkout.getCustomerId(), checkout.getBasket());

        // Map from Domain to API and return
        CheckoutResponseDto response = CheckoutResponseDto.of(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
