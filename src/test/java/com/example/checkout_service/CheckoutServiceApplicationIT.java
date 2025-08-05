package com.example.checkout_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.checkout_service.api.dto.CheckoutRequestDto;
import com.example.checkout_service.api.dto.CheckoutResponseDto;
import com.example.checkout_service.api.dto.ItemDto;

@SpringBootTest(classes = CheckoutServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckoutServiceApplicationIT {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        private static final String BASE_URL = "http://localhost:";

        @Test
        void testValidPostCheckoutRequest() {
                CheckoutRequestDto request = new CheckoutRequestDto("C1001",
                                List.of(
                                                new ItemDto("P100", 1),
                                                new ItemDto("P101", 1),
                                                new ItemDto("P200", 2)));

                CheckoutResponseDto response = this.restTemplate
                                .postForObject(BASE_URL + port + "/api/checkout", request, CheckoutResponseDto.class);

                assertEquals(response.items().size(), 3);
                assertEquals(469.961, response.totalSum());
        }

        @Test
        void testInvalidCustomerPostCheckoutRequest() {
                CheckoutRequestDto request = new CheckoutRequestDto("invalid",
                                List.of(
                                                new ItemDto("P100", 1),
                                                new ItemDto("P101", 1),
                                                new ItemDto("P200", 2)));

                ResponseEntity<CheckoutResponseDto> response = this.restTemplate
                                .postForEntity(BASE_URL + port + "/api/checkout", request, CheckoutResponseDto.class);
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        void testInvalidItemPostCheckoutRequest() {
                CheckoutRequestDto request = new CheckoutRequestDto("C1001",
                                List.of(
                                                new ItemDto("invalid", 1),
                                                new ItemDto("P101", 1),
                                                new ItemDto("P200", 2)));

                ResponseEntity<CheckoutResponseDto> response = this.restTemplate
                                .postForEntity(BASE_URL + port + "/api/checkout", request, CheckoutResponseDto.class);
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        void testEmptyItemsPostCheckoutRequest() {
                CheckoutRequestDto request = new CheckoutRequestDto("C1001", Collections.emptyList());

                ResponseEntity<CheckoutResponseDto> response = this.restTemplate
                                .postForEntity(BASE_URL + port + "/api/checkout", request, CheckoutResponseDto.class);
                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

}
