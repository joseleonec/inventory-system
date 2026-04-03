package com.myenterprise.inventory.domain.dto.transaction;

import com.myenterprise.inventory.domain.enums.ProductTransactionType;

public record TransactionCreateDTO(
        Long productId,
        ProductTransactionType type,
        int quantity
        ) {

}
