package com.myenterprise.inventory.domain.ports.input;

import java.util.List;

import com.myenterprise.inventory.domain.models.Product;

public interface ProductInPort {

    Product create(Product p);

    Product update(Product p);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findAll();

}
