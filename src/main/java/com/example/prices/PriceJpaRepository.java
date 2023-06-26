package com.example.prices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Integer brandId, Integer productId, LocalDateTime date, LocalDateTime date2);
}