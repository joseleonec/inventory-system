package com.myenterprise.inventory.infrastructure.adapters.output.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.myenterprise.inventory.domain.models.Stock;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.StockEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductOutAdapterMapper.class, TransactionOutAdapterMapper.class})
public interface StockOutAdapterMapper {

    @Mapping(target = "product", source = "stock.product")
    @Mapping(target = "lastTransaction", source = "stock.lastTransaction")
    StockEntity toEntity(Stock stock);

    Stock toDomain(StockEntity stockEntity);

}
