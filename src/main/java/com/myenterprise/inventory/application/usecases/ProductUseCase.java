package com.myenterprise.inventory.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myenterprise.inventory.domain.models.Product;
import com.myenterprise.inventory.domain.ports.input.ProductInPort;
import com.myenterprise.inventory.domain.ports.output.ProductOutPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductInPort {

    private final ProductOutPort productOutPort;

    @Override
    public Product create(Product p) {

        return productOutPort.create(p);
    }

    @Override
    public Product update(Product p) {

        return productOutPort.update(p);
    }

    @Override
    public void delete(Long id) {

        productOutPort.delete(id);
    }

    @Override
    public Product findById(Long id) {

        return productOutPort.findById(id);
    }

    @Override
    public List<Product> findAll() {

        return productOutPort.findAll();
    }

}
