package com.myenterprise.inventory.domain.models;

import com.myenterprise.inventory.domain.enums.ProductTransactionType;

import lombok.Data;

@Data
public class ProductTransaction {

    private Long id;

    private Product product;

    private ProductTransactionType transactionType;

    private String transactionData;

    private int quantity;

    private Boolean isActive = true;

}
