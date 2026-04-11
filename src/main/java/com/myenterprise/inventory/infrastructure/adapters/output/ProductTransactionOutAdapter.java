package com.myenterprise.inventory.infrastructure.adapters.output;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myenterprise.inventory.domain.models.ProductTransaction;
import com.myenterprise.inventory.domain.ports.output.ProductTransactionOutPort;
import com.myenterprise.inventory.infrastructure.adapters.output.mappers.TransactionOutAdapterMapper;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductTransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTransactionOutAdapter implements ProductTransactionOutPort {

    private final ProductTransactionRepository productTransactionRepository;

    private final TransactionOutAdapterMapper transactionOutAdapterMapper;

    @Override
    public ProductTransaction create(ProductTransaction transaction) {

        var entity = transactionOutAdapterMapper.toEntity(transaction);

        var savedEntity = productTransactionRepository.save(entity);

        return transactionOutAdapterMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(Long id) {

        var entity = productTransactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found"));

        productTransactionRepository.delete(entity);
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

}
