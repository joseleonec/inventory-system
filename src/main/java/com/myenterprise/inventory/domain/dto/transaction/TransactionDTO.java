package com.myenterprise.inventory.domain.dto.transaction;

import com.myenterprise.inventory.domain.dto.product.ProductDTO;
import com.myenterprise.inventory.domain.enums.ProductTransactionType;

public record TransactionDTO(
        Long id,
        ProductDTO product,
        ProductTransactionType transactionType,
        String transactionData,
        int quantity
        ) {

}
