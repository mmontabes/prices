package com.project.prices.adapter.persistence;

import com.project.prices.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceRepositoryAdapterIntegrationTest {

    @Autowired
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    void findApplicablePrice_shouldReturnCorrectPrice_whenInRange() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(date, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("35.50"), result.get().getPrice()); // priority más alto
    }

    @Test
    void findApplicablePrice_shouldReturnEmpty_whenNoPriceMatches() {
        LocalDateTime date = LocalDateTime.of(2030, 1, 1, 0, 0);
        Long productId = 999L;
        Long brandId = 999L;

        Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(date, productId, brandId);

        assertTrue(result.isEmpty());
    }
}
