package com.myenterprise.inventory.infrastructure.adapters.input.mappers;

import org.mapstruct.Mapper;

import com.myenterprise.inventory.domain.dto.transaction.TransactionCreateDTO;
import com.myenterprise.inventory.domain.dto.transaction.TransactionDTO;
import com.myenterprise.inventory.domain.models.ProductTransaction;

@Mapper(componentModel = "spring")
public interface TransactionInAdapterMapper {

    TransactionDTO toDTO(ProductTransaction transaction);

    ProductTransaction toDomain(TransactionCreateDTO transaction);

}
