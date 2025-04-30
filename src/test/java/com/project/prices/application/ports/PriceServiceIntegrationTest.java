package com.project.prices.application.ports;

import com.project.prices.application.ports.PriceService;
import com.project.prices.application.ports.out.PriceRepositoryPort;
import com.project.prices.domain.model.Price;
import com.project.prices.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceServiceIntegrationTest {

    @Autowired
    private PriceService priceService;

    @Test
    void getPrice_ShouldReturnPrice_WhenDataExists() {
        // Use a date that falls within the range of the data in data.sql
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0); // entre 15:00 y 18:30
        Long productId = 35455L;
        Long brandId = 1L;

        Price result = priceService.getPrice(date, productId, brandId);

        assertNotNull(result);
        assertEquals(new BigDecimal("35.50"), result.getPrice()); // price de price_list 2
    }

    @Test
    void getPrice_ShouldThrowException_WhenNoData() {
        LocalDateTime date = LocalDateTime.of(2025, 6, 14, 10, 0);
        Long productId = 999L;
        Long brandId = 999L;

        assertThrows(PriceNotFoundException.class, () ->
                priceService.getPrice(date, productId, brandId)
        );
    }
}
