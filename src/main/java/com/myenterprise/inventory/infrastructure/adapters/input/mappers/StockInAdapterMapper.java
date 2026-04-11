package com.myenterprise.inventory.infrastructure.adapters.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.myenterprise.inventory.domain.dto.stock.StockDTO;
import com.myenterprise.inventory.domain.models.Stock;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockInAdapterMapper {

    StockDTO toDTO(Stock stock);

}
