package com.myenterprise.inventory.domain.dto.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myenterprise.inventory.domain.enums.ProductTransactionType;

public record TransactionDTO(
        Long id,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long productId,
        ProductTransactionType transactionType,
        String transactionData,
        int quantity
        ) {

}
