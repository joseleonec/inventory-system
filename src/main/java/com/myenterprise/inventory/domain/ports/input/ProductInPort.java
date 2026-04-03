package com.myenterprise.inventory.domain.ports.input;

import com.myenterprise.inventory.domain.models.Product;
import java.util.List;

public interface ProductInPort {
    
    Product create(Product p);

    Product update(Product p);

    Product delete(String id);

    Product findById(String id);

    List<Product> findAll();
    
}
