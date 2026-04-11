package com.myenterprise.inventory.domain.models;

import lombok.Data;

@Data
public class Stock {

    private Long id;

    private Product product;

    private ProductTransaction lastTransaction;

    private int currentQuantity;

}
