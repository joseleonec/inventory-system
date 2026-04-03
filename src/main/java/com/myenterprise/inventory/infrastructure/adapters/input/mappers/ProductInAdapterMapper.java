package com.myenterprise.inventory.infrastructure.adapters.input.mappers;

import org.mapstruct.Mapper;

import com.myenterprise.inventory.domain.dto.product.ProductCreateDTO;
import com.myenterprise.inventory.domain.dto.product.ProductDTO;
import com.myenterprise.inventory.domain.models.Product;

@Mapper(componentModel = "spring")
public interface ProductInAdapterMapper {

    Product toDomain(ProductCreateDTO productCreateDTO);

    ProductDTO toDTO(Product product);

}
