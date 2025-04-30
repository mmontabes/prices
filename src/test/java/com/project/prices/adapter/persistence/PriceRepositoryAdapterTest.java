package com.project.prices.adapter.persistence;

import com.project.prices.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceRepositoryAdapterTest {

    private PriceRepository priceRepository;
    private PriceRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        priceRepository = mock(PriceRepository.class);
        adapter = new PriceRepositoryAdapter(priceRepository);
    }

    @Test
    void findApplicablePrice_shouldReturnPriceWithHighestPriority() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity lowPriority = PriceEntity.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(date.minusDays(1))
                .endDate(date.plusDays(1))
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .priority(0)
                .build();

        PriceEntity highPriority = PriceEntity.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(2)
                .startDate(date.minusHours(2))
                .endDate(date.plusHours(2))
                .price(new BigDecimal("25.45"))
                .curr("EUR")
                .priority(1)
                .build();

        when(priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                productId, brandId, date, date)).thenReturn(
                new ArrayList<>(List.of(lowPriority, highPriority))
        );

        // Act
        Optional<Price> result = adapter.findApplicablePrice(date, productId, brandId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("25.45"), result.get().getPrice());
    }

    @Test
    void findApplicablePrice_shouldReturnEmpty_whenNoPricesMatch() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                anyLong(), anyLong(), eq(date), eq(date))).thenReturn(
                new ArrayList<>()
        );

        Optional<Price> result = adapter.findApplicablePrice(date, 999L, 999L);

        assertTrue(result.isEmpty());
    }

    @Test
    void findApplicablePrice_shouldReturnFirst_whenMultipleWithSamePriority() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 15, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity price1 = PriceEntity.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(3)
                .startDate(date.minusHours(1))
                .endDate(date.plusHours(1))
                .price(new BigDecimal("40.00"))
                .curr("EUR")
                .priority(1)
                .build();

        PriceEntity price2 = PriceEntity.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(4)
                .startDate(date.minusHours(1))
                .endDate(date.plusHours(1))
                .price(new BigDecimal("50.00"))
                .curr("EUR")
                .priority(1)
                .build();

        when(priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                productId, brandId, date, date)).thenReturn(
                new ArrayList<>(List.of(price1, price2))
        );

        Optional<Price> result = adapter.findApplicablePrice(date, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("40.00"), result.get().getPrice());
    }

    @Test
    void findApplicablePrice_shouldReturnOnlyResult_whenOnlyOneExists() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 16, 12, 0);
        Long productId = 111L;
        Long brandId = 2L;

        PriceEntity price = PriceEntity.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(5)
                .startDate(date.minusDays(1))
                .endDate(date.plusDays(1))
                .price(new BigDecimal("99.99"))
                .curr("USD")
                .priority(0)
                .build();

        when(priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                productId, brandId, date, date)).thenReturn(
                new ArrayList<>(List.of(price))
        );

        Optional<Price> result = adapter.findApplicablePrice(date, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(new BigDecimal("99.99"), result.get().getPrice());
        assertEquals("USD", result.get().getCurr());
    }
}
