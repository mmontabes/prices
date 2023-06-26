package com.example.prices.application.ports.incoming;

import com.example.prices.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface PriceService {

    Price findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate);

}
