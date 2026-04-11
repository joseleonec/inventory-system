package com.myenterprise.inventory.domain.ports.output;

import java.util.List;

import com.myenterprise.inventory.domain.models.Stock;

public interface StockOutPort {

    List<Stock> findAll();

    Stock findByProductId(Long productId);

    Stock recalculateStock(Long productId);

    Stock update(Long productId, Long lastTransactionId);

}
