package com.myenterprise.inventory.application.usecases;

import java.util.List;

import com.myenterprise.inventory.domain.models.ProductTransaction;
import com.myenterprise.inventory.domain.ports.input.ProductTransactionInPort;
import com.myenterprise.inventory.domain.ports.output.ProductTransactionOutPort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductTransactionUseCase implements ProductTransactionInPort {

    private final ProductTransactionOutPort productTransactionOutPort;

    @Override
    public ProductTransaction create(ProductTransaction transaction) {

        return productTransactionOutPort.create(transaction);
    }

    @Override
    public ProductTransaction findById(Long id) {

        return productTransactionOutPort.findById(id);
    }

    @Override
    public List<ProductTransaction> findAll() {

        return productTransactionOutPort.findAll();
    }

    @Override
    public List<ProductTransaction> findByProductId(Long productId) {

        return productTransactionOutPort.findByProductId(productId);
    }

}
