package com.project.prices.adapter.rest;

import com.project.prices.application.ports.in.PriceQueryPort;
import com.project.prices.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    private PriceQueryPort priceQueryPort;
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        priceQueryPort = mock(PriceQueryPort.class);
        priceController = new PriceController(priceQueryPort);
    }

    @Test
    void getPrice_returnsPrice_whenValidInput() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Price mockPrice = Price.builder()
                .priceList(1)
                .brandId(brandId)
                .productId(productId)
                .startDate(date.minusDays(1))
                .endDate(date.plusDays(1))
                .price(BigDecimal.valueOf(35.50))
                .build();

        when(priceQueryPort.getPrice(date, productId, brandId)).thenReturn(mockPrice);

        // Act
        ResponseEntity<Price> response = priceController.getPrice(date, productId, brandId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPrice, response.getBody());

        verify(priceQueryPort, times(1)).getPrice(date, productId, brandId);
    }
    @Test
    void getPrice_throwsException_whenServiceFails() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceQueryPort.getPrice(date, productId, brandId))
                .thenThrow(new RuntimeException("Internal error"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> priceController.getPrice(date, productId, brandId)
        );

        assertEquals("Internal error", exception.getMessage());
        verify(priceQueryPort, times(1)).getPrice(date, productId, brandId);
    }
}
