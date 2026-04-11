package com.myenterprise.inventory.domain.ports.output;

import java.util.List;

import com.myenterprise.inventory.domain.models.ProductTransaction;

public interface ProductTransactionOutPort {

    ProductTransaction create(ProductTransaction transaction);

    void delete(Long id);

    ProductTransaction findById(Long id);

    List<ProductTransaction> findAll();

    List<ProductTransaction> findByProductId(Long productId);

}
