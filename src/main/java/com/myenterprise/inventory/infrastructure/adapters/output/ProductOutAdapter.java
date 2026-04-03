package com.myenterprise.inventory.infrastructure.adapters.output;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.myenterprise.inventory.domain.models.Product;
import com.myenterprise.inventory.domain.ports.output.ProductOutPort;
import com.myenterprise.inventory.infrastructure.adapters.output.mappers.ProductOutAdapterMapper;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductOutAdapter implements ProductOutPort {

    private final ProductRepository productRepository;

    private final ProductOutAdapterMapper productOutAdapterMapper;

    @Override
    public Product create(Product p) {

        var entity = productOutAdapterMapper.toEntity(p);

        var savedEntity = productRepository.save(entity);

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

        var entity = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));

        productRepository.delete(entity);
    }

    @Override
    public Product findById(Long id) {

        var entity = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found"));

        return productOutAdapterMapper.toDomain(entity);
    }

    @Override
    public List<Product> findAll() {

        var entities = productRepository.findAll();

        var domainList = entities.parallelStream().map(productOutAdapterMapper::toDomain).toList();

        return domainList;
    }

}
