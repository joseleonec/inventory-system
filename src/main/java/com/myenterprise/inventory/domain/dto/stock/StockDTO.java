package com.myenterprise.inventory.domain.dto.stock;

import com.myenterprise.inventory.domain.dto.product.ProductDTO;
import com.myenterprise.inventory.domain.dto.transaction.TransactionDTO;

public record StockDTO(
        Long id,
        ProductDTO product,
        TransactionDTO lastTransaction,
        int currentQuantity
) {

}
