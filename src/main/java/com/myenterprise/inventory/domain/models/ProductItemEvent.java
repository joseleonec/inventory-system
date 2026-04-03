package com.myenterprise.inventory.domain.models;

import com.myenterprise.inventory.domain.enums.ProductItemEventType;

import lombok.Data;

@Data
public class ProductItemEvent {

    private String id;

    private ProductItem productItem;

    private ProductItemEventType eventType;

    private String eventData;

    private int quantity;

}
