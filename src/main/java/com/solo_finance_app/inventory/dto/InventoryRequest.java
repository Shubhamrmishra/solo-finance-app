package com.solo_finance_app.inventory.dto;

import lombok.Data;

@Data
public class InventoryRequest {

    private String itemName;

    private Integer quantity;

    private Double price;

    private Integer lowStockThreshold;
}