package com.myenterprise.inventory.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myenterprise.inventory.domain.models.Stock;
import com.myenterprise.inventory.domain.ports.input.StockInPort;
import com.myenterprise.inventory.domain.ports.output.StockOutPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockUseCase implements StockInPort {

    private final StockOutPort stockOutPort;

    @Override
    public List<Stock> findAll() {
        return stockOutPort.findAll();
    }

    @Override
    public Stock findByProductId(Long productId) {
        return stockOutPort.findByProductId(productId);
    }

    @Override
    public Stock recalculateStock(Long productId) {

        return stockOutPort.recalculateStock(productId);
    }

}
