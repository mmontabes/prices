package com.project.prices.application.ports;


import com.project.prices.application.ports.in.PriceQueryPort;
import com.project.prices.application.ports.out.PriceRepositoryPort;
import com.project.prices.domain.model.Price;
import com.project.prices.exceptions.PriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService implements PriceQueryPort {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    // Retrieves the applicable price for a product on a specific date and brand.
    @Override
    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepositoryPort.findApplicablePrice(date, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException("No price found for the given parameters"));
    }
}
