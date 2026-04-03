package com.myenterprise.inventory.infrastructure.adapters.output.mappers;

import org.mapstruct.Mapper;

import com.myenterprise.inventory.domain.models.Product;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductOutAdapterMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productEntity);

}
