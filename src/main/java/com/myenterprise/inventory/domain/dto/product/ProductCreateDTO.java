package com.myenterprise.inventory.domain.dto.product;

import java.math.BigDecimal;

public record ProductCreateDTO(
                String name,
                String description,
                BigDecimal price,
                Integer stock) {

}
