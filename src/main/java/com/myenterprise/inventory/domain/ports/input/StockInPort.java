package com.myenterprise.inventory.domain.ports.input;

import java.util.List;

import com.myenterprise.inventory.domain.models.Stock;

public interface StockInPort {

    List<Stock> findAll();

    Stock findByProductId(Long productId);

    Stock recalculateStock(Long productId);

}
