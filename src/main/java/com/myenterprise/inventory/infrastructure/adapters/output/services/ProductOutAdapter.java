package com.myenterprise.inventory.infrastructure.adapters.output.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myenterprise.inventory.application.exceptions.ProductNotFoundException;
import com.myenterprise.inventory.domain.models.Product;
import com.myenterprise.inventory.domain.ports.output.ProductOutPort;
import com.myenterprise.inventory.domain.ports.output.StockOutPort;
import com.myenterprise.inventory.infrastructure.adapters.output.mappers.ProductOutAdapterMapper;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductOutAdapter implements ProductOutPort {

    private final ProductRepository productRepository;

    private final ProductOutAdapterMapper productOutAdapterMapper;

    private final StockOutPort stockRepository;

    @Override
    @Transactional
    public Product create(Product p) {

        var entity = productOutAdapterMapper.toEntity(p);

        var savedEntity = productRepository.save(entity);

        stockRepository.recalculateStock(savedEntity.getId());

        return productOutAdapterMapper.toDomain(savedEntity);
    }

    @Override
    public Product update(Product p) {

        var entity = productOutAdapterMapper.toEntity(p);

        var savedEntity = productRepository.save(entity);

        return productOutAdapterMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Long id) {

        var entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        entity.setIsActive(false);

        productRepository.save(entity);
    }

    @Override
    public Product findById(Long id) {

        var entity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        return productOutAdapterMapper.toDomain(entity);
    }

    @Override
    public List<Product> findAll() {

        var entities = productRepository.findAll();

        var domainList = entities.parallelStream().map(productOutAdapterMapper::toDomain).toList();

        return domainList;
    }

}
