package com.example.prices.application.ports.outgoing.rest;

import com.example.prices.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate);

}
