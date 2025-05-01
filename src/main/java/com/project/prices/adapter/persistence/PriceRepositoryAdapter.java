package com.project.prices.adapter.persistence;

import com.project.prices.application.ports.out.PriceRepositoryPort;
import com.project.prices.domain.model.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    public PriceRepositoryAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    // Searching for the applicable price for a given product, brand, and date.
    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime date, Long productId, Long brandId) {
        // Query the database to retrieve prices that match the parameters, sorted by descending priority.
        List<PriceEntity> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                productId, brandId, date, date);


        // If no applicable prices are found, return an empty optional.
        if (prices.isEmpty()) {
            return Optional.empty();
        }

        // Sort the list by priority in ascending order
        prices.sort((p1, p2) -> Integer.compare(p2.getPriority(), p1.getPriority()));



        PriceEntity priceEntity = prices.get(0);
        return Optional.of(new Price(priceEntity.getProductId(), priceEntity.getBrandId(),
                priceEntity.getPriceList(), priceEntity.getStartDate(),
                priceEntity.getEndDate(), priceEntity.getPrice(), priceEntity.getCurr()));
    }
}
