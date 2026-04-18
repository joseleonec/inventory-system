package com.myenterprise.inventory.domain.dto.product;

import java.math.BigDecimal;

import com.myenterprise.inventory.domain.enums.MeasureUnitEnum;

public record ProductCreateDTO(
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        MeasureUnitEnum measureUnit) {

}
