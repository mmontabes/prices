package com.example.prices.infrastructure.adapters;

import com.example.prices.Price;
import com.example.prices.application.ports.outgoing.rest.PriceRepository;
import com.example.prices.infrastructure.persistence.sqlserver.jpa.mappers.PriceEntityMapper;
import com.example.prices.PriceJpaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceServiceAdapter implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceEntityMapper;


    public Price findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {

        List<Price> prices = priceEntityMapper.toList(priceJpaRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, applicationDate, applicationDate));

        if (!prices.isEmpty()) {
            return prices.get(0);
        }

        return null;
    }
}
