package com.example.prices.infrastructure.persistence.sqlserver.jpa.mappers;

import com.example.prices.Price;
import com.example.prices.PriceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    List<Price> toList(List<PriceEntity> priceEntities);

}
