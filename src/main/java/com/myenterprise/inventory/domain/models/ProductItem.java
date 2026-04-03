package com.myenterprise.inventory.domain.models;

import lombok.Data;

@Data
public class ProductItem {

    private String id;

    private Product product;

    private int quantity;

}
