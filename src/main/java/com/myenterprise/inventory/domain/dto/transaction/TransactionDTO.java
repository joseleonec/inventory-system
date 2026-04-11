package com.myenterprise.inventory.domain.dto.transaction;

import com.myenterprise.inventory.domain.enums.ProductTransactionType;

public record TransactionDTO(
        Long id,
        Long productId,
        ProductTransactionType transactionType,
        String transactionData,
        int quantity
        ) {

}
