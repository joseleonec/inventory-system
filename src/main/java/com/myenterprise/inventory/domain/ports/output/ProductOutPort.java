package com.myenterprise.inventory.domain.ports.output;

import java.util.List;

import com.myenterprise.inventory.domain.models.Product;

public interface ProductOutPort {

    Product create(Product p);

    Product update(Product p);

    Product delete(Long id);

    Product findById(Long id);

    List<Product> findAll();

}
