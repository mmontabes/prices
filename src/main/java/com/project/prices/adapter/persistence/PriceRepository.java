package com.project.prices.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
            Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}