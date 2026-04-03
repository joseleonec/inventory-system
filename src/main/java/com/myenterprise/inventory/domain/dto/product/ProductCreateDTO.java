package com.myenterprise.inventory.domain.dto.product;

import java.math.BigDecimal;

public record ProductCreateDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock
        ) {

}
