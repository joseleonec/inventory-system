package com.myenterprise.inventory.infrastructure.adapters.output.mappers;

import org.mapstruct.Mapper;

import com.myenterprise.inventory.domain.models.ProductTransaction;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.ProductTransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionOutAdapterMapper {

    ProductTransactionEntity toEntity(ProductTransaction transaction);

    ProductTransaction toDomain(ProductTransactionEntity transactionEntity);

}
