package com.myenterprise.inventory.infrastructure.adapters.output.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.myenterprise.inventory.domain.models.ProductTransaction;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.ProductTransactionEntity;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = ProductOutAdapterMapper.class
)
public interface TransactionOutAdapterMapper {

    @Mapping(target = "product", source = "transaction.product")
    ProductTransactionEntity toEntity(ProductTransaction transaction);

    ProductTransaction toDomain(ProductTransactionEntity transactionEntity);

}
