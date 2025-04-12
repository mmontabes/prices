package com.project.prices.application.ports.out;


import com.project.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId);
}
