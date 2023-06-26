package com.example.prices.application.ports.services;


import com.example.prices.Price;
import com.example.prices.application.ports.incoming.PriceService;
import com.example.prices.application.ports.outgoing.rest.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class DefaultPriceService implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Price findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return priceRepository.findPrice(brandId,productId,applicationDate);
    }
}

