package com.myenterprise.inventory.domain.ports.input;

import java.util.List;

import com.myenterprise.inventory.domain.models.ProductTransaction;

public interface ProductTransactionInPort {

    ProductTransaction create(ProductTransaction transaction);

    ProductTransaction findById(Long id);

    List<ProductTransaction> findAll();

    List<ProductTransaction> findByProductId(Long productId);

}
