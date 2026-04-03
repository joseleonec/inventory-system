package com.myenterprise.inventory.infrastructure.adapters.input.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myenterprise.inventory.domain.dto.product.ProductDTO;
import com.myenterprise.inventory.domain.ports.input.ProductInPort;
import com.myenterprise.inventory.infrastructure.adapters.input.mappers.ProductInAdapterMapper;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductInPort productPort;

    private ProductInAdapterMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {

        var product = productPort.findById(id);

        var dto = productMapper.toDTO(product);

        return ResponseEntity.ok(dto);
        
    }

}
