package com.myenterprise.inventory.infrastructure.adapters.output.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myenterprise.inventory.application.exceptions.ProductNotFoundException;
import com.myenterprise.inventory.domain.models.ProductTransaction;
import com.myenterprise.inventory.domain.ports.output.ProductTransactionOutPort;
import com.myenterprise.inventory.domain.ports.output.StockOutPort;
import com.myenterprise.inventory.infrastructure.adapters.output.mappers.TransactionOutAdapterMapper;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductRepository;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductTransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTransactionOutAdapter implements ProductTransactionOutPort {

    private final ProductTransactionRepository productTransactionRepository;

    private final TransactionOutAdapterMapper transactionOutAdapterMapper;

    private final ProductRepository productRepository;

    private final StockOutPort stockOutPort;

    @Override
    @Transactional
    public ProductTransaction create(ProductTransaction transaction) {

        var productId = transaction.getProduct().getId();

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var ttransactionEntity = transactionOutAdapterMapper.toEntity(transaction);

        var savedTransactionEntity = productTransactionRepository.save(ttransactionEntity);

        var trxDomain = transactionOutAdapterMapper.toDomain(savedTransactionEntity);
        stockOutPort.update(productId, savedTransactionEntity.getId());

        return trxDomain;
    }

    @Override
    public ProductTransaction findById(Long id) {

        var entity = productTransactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found"));

        return transactionOutAdapterMapper.toDomain(entity);
    }

    @Override
    public List<ProductTransaction> findAll() {

        return productTransactionRepository.findAll().stream()
                .map(transactionOutAdapterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductTransaction> findByProductId(Long productId) {

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        return productTransactionRepository.findByProductId(productId).stream()
                .map(transactionOutAdapterMapper::toDomain)
                .collect(Collectors.toList());
    }

}
