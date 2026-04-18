package com.myenterprise.inventory.infrastructure.adapters.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.myenterprise.inventory.domain.dto.product.ProductCreateDTO;
import com.myenterprise.inventory.domain.dto.product.ProductDTO;
import com.myenterprise.inventory.domain.enums.MeasureUnitEnum;
import com.myenterprise.inventory.domain.models.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductInAdapterMapper {

    Product toDomain(ProductCreateDTO productCreateDTO);

    ProductDTO toDTO(Product product);

    default String map(MeasureUnitEnum measureUnit) {
        return measureUnit == null ? null : measureUnit.toJsonValue();
    }

    default MeasureUnitEnum map(String measureUnit) {
        return measureUnit == null ? null : MeasureUnitEnum.fromValue(measureUnit);
    }

}
