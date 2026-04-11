package com.myenterprise.inventory.infrastructure.adapters.input.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myenterprise.inventory.domain.dto.stock.StockDTO;
import com.myenterprise.inventory.domain.ports.input.StockInPort;
import com.myenterprise.inventory.infrastructure.adapters.input.mappers.StockInAdapterMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockInPort stockInPort;

    private final StockInAdapterMapper stockMapper;

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {

        var stocks = stockInPort.findAll();

        var dtos = stocks.stream()
                .map(stockMapper::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockDTO> getStockByProduct(@PathVariable Long productId) {

        var stock = stockInPort.findByProductId(productId);

        var dto = stockMapper.toDTO(stock);

        return ResponseEntity.ok(dto);
    }

}
