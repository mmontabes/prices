package com.example.prices.interfaces.rest.prices;


import com.example.prices.application.ports.incoming.PriceService;
import com.example.prices.infrastructure.adapters.PriceServiceAdapter;
import com.example.prices.interfaces.rest.dtos.PriceResponse;
import com.example.prices.interfaces.rest.mappers.PriceMapper;
import com.example.prices.openapi.interfaces.rest.ConsultaPreciosApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
@Slf4j

public class PriceController implements ConsultaPreciosApi {

    private final PriceService priceService;
    private final PriceMapper priceMapper;




    @Override
    public ResponseEntity<PriceResponse> searchPrices(@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate applicationDate,
                                                      @RequestParam("productId") Integer productId,
                                                      @RequestParam("brandId") Integer brandId) {
        var price = priceMapper.toDTO(priceService.findPrice(brandId, productId, LocalDateTime.from(applicationDate)));

        if (price != null) {
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();

            }
        }
    }


