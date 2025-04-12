package com.project.prices.application.ports.in;


import com.project.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceQueryPort {
    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
