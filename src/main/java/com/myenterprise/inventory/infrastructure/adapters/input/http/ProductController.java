package com.myenterprise.inventory.infrastructure.adapters.input.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myenterprise.inventory.domain.dto.product.ProductCreateDTO;
import com.myenterprise.inventory.domain.dto.product.ProductDTO;
import com.myenterprise.inventory.domain.ports.input.ProductInPort;
import com.myenterprise.inventory.infrastructure.adapters.input.mappers.ProductInAdapterMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductInPort productInPort;

    private final ProductInAdapterMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {

        var product = productInPort.findById(id);

        var dto = productMapper.toDTO(product);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductCreateDTO productDTO) {

        var product = productMapper.toDomain(productDTO);

        var savedProduct = productInPort.create(product);

        var dto = productMapper.toDTO(savedProduct);

        return ResponseEntity.ok(dto);
    }

}
