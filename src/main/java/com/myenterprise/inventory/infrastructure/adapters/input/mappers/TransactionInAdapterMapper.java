package com.myenterprise.inventory.infrastructure.adapters.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.myenterprise.inventory.domain.dto.transaction.TransactionCreateDTO;
import com.myenterprise.inventory.domain.dto.transaction.TransactionDTO;
import com.myenterprise.inventory.domain.models.ProductTransaction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionInAdapterMapper {

    TransactionDTO toDTO(ProductTransaction transaction);

    @Mapping(target = "product.id", source = "transaction.productId")
    ProductTransaction toDomain(TransactionCreateDTO transaction);

}
