package com.myenterprise.inventory.infrastructure.adapters.output.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myenterprise.inventory.application.exceptions.ProductNotFoundException;
import com.myenterprise.inventory.domain.enums.ProductTransactionType;
import com.myenterprise.inventory.domain.models.Stock;
import com.myenterprise.inventory.domain.ports.output.StockOutPort;
import com.myenterprise.inventory.infrastructure.adapters.output.mappers.StockOutAdapterMapper;
import com.myenterprise.inventory.infrastructure.adapters.output.mappers.TransactionOutAdapterMapper;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.entities.StockEntity;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductRepository;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.ProductTransactionRepository;
import com.myenterprise.inventory.infrastructure.adapters.output.persistence.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockOutAdapter implements StockOutPort {

    private final ProductRepository productRepository;

    private final ProductTransactionRepository productTransactionRepository;

    private final TransactionOutAdapterMapper transactionOutAdapterMapper;

    private final StockRepository stockRepository;

    private final StockOutAdapterMapper stockOutAdapterMapper;

    @Override
    public List<Stock> findAll() {

        return stockRepository.findAll().stream()
                .map(this.stockOutAdapterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Stock findByProductId(Long productId) {

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var stockEntity = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new NoSuchElementException("Stock not found for product ID: " + productId));

        return stockOutAdapterMapper.toDomain(stockEntity);
    }

    @Override
    public Stock update(Long productId, Long lastTransactionId) {

        var lastTransactionEntity = productTransactionRepository.findById(lastTransactionId)
                .orElseThrow(() -> new NoSuchElementException("Transaction not found with ID: " + lastTransactionId));

        var stockEntity = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new NoSuchElementException("Stock not found for product ID: " + productId));

        int quantityChange = 0;
        if (lastTransactionEntity.getTransactionType() == ProductTransactionType.ADD_ITEM) {
            quantityChange = lastTransactionEntity.getQuantity();
        } else if (lastTransactionEntity.getTransactionType() == ProductTransactionType.REMOVE_ITEM) {
            quantityChange = -lastTransactionEntity.getQuantity();
        }

        stockEntity.setCurrentQuantity(stockEntity.getCurrentQuantity() + quantityChange);
        stockEntity.setLastTransaction(lastTransactionEntity);

        var savedStockEntity = stockRepository.save(stockEntity);

        return stockOutAdapterMapper.toDomain(savedStockEntity);
    }

    @Override
    public Stock recalculateStock(Long productId) {

        var productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        var transactions = productTransactionRepository.findByProductId(productEntity.getId());

        int currentQuantity = 0;
        if (!transactions.isEmpty()) {
            currentQuantity = transactions.stream()
                    .mapToInt(t -> {
                        if (t.getTransactionType() == ProductTransactionType.ADD_ITEM) {
                            return t.getQuantity();
                        } else if (t.getTransactionType() == ProductTransactionType.REMOVE_ITEM) {
                            return -t.getQuantity();
                        }
                        return 0;
                    })
                    .sum();
        }

        var lastTransactionEntity = transactions.stream()
                .max((t1, t2) -> t1.getId().compareTo(t2.getId()))
                .orElse(null);

        var stockEntity = stockRepository.findByProductId(productId).orElseGet(() -> {
            var newStock = new StockEntity();
            newStock.setProduct(productEntity);
            return newStock;
        });
        stockEntity.setLastTransaction(lastTransactionEntity);
        stockEntity.setCurrentQuantity(currentQuantity);

        var savedStockEntity = stockRepository.save(stockEntity);

        return stockOutAdapterMapper.toDomain(savedStockEntity);
    }

}
