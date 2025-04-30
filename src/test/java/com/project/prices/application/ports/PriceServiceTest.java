package com.project.prices.application.ports;

import com.project.prices.application.ports.out.PriceRepositoryPort;
import com.project.prices.domain.model.Price;
import com.project.prices.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    private PriceRepositoryPort priceRepositoryPort;
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceRepositoryPort = mock(PriceRepositoryPort.class);
        priceService = new PriceService(priceRepositoryPort);
    }

    @Test
    void getPrice_ShouldReturnPrice_WhenApplicablePriceExists() {
        // Arrange
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        Price expectedPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(date.minusDays(1))
                .endDate(date.plusDays(1))
                .price(new BigDecimal("99.99"))
                .curr("EUR")
                .build();

        when(priceRepositoryPort.findApplicablePrice(date, productId, brandId))
                .thenReturn(Optional.of(expectedPrice));

        // Act
        Price actualPrice = priceService.getPrice(date, productId, brandId);

        // Assert
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void getPrice_ShouldThrowException_WhenNoPriceFound() {
        // Arrange
        LocalDateTime date = LocalDateTime.now();
        Long productId = 1L;
        Long brandId = 1L;

        when(priceRepositoryPort.findApplicablePrice(date, productId, brandId))
                .thenReturn(Optional.empty());

        // Assert
        assertThrows(PriceNotFoundException.class, () ->
                priceService.getPrice(date, productId, brandId)
        );
    }
}
