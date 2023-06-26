package com.example.prices.interfaces.rest.mappers;

import com.example.prices.Price;
import com.example.prices.PriceEntity;
import com.example.prices.interfaces.rest.dtos.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PriceMapper {

    PriceResponse toDTO(Price price);

    Price toEntity(PriceResponse priceResponse);

}

