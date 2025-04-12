package com.project.prices.domain.port;


import com.project.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceQuery {
    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
