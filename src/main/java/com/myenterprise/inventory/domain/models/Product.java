package com.myenterprise.inventory.domain.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

}
