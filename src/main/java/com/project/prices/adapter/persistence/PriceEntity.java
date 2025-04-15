package com.project.prices.adapter.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "price_entity")
@AllArgsConstructor
@Builder
public class PriceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "BRAND_ID", nullable = false)
        private Long brandId;
        @Column(name = "START_DATE", nullable = false)
        private LocalDateTime startDate;
        @Column(name = "END_DATE", nullable = false)
        private LocalDateTime endDate;
        @Column(name = "PRICE_LIST", nullable = false)
        private Integer priceList;
        @Column(name = "PRODUCT_ID", nullable = false)
        private Long productId;
        @Column(name = "PRIORITY", nullable = false)
        private Integer priority;
        @Column(name = "PRICE", nullable = false)
        private BigDecimal price;
        @Column(name = "CURR", nullable = false)
        private String curr;

        public PriceEntity() {
                brandId=0L;
                startDate= LocalDateTime.now();
                endDate= LocalDateTime.now();
                priceList=0;
                productId=0L;
                priority=0;
                price= BigDecimal.valueOf(0);
                curr="NZD";


        }

        public Integer getPriority() {
                return priority;
        }

        public void setPriority(Integer priority) {
                this.priority = priority;
        }
}