package com.myenterprise.inventory.infrastructure.adapters.input.http;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myenterprise.inventory.domain.dto.transaction.TransactionCreateDTO;
import com.myenterprise.inventory.domain.dto.transaction.TransactionDTO;
import com.myenterprise.inventory.domain.ports.input.ProductTransactionInPort;
import com.myenterprise.inventory.infrastructure.adapters.input.mappers.TransactionInAdapterMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class ProductTransactionController {

    private final ProductTransactionInPort productTransactionInPort;

    private final TransactionInAdapterMapper transactionMapper;

    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionCreateDTO transactionDTO) {

        var transaction = transactionMapper.toDomain(transactionDTO);

        var savedTransaction = productTransactionInPort.create(transaction);

        var dto = transactionMapper.toDTO(savedTransaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {

        var transaction = productTransactionInPort.findById(id);

        var dto = transactionMapper.toDTO(transaction);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByProduct(@PathVariable Long productId) {

        var transactions = productTransactionInPort.findByProductId(productId);

        var dtos = transactions.stream()
                .map(transactionMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {

        var transactions = productTransactionInPort.findAll();

        var dtos = transactions.stream()
                .map(transactionMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

}
