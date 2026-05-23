package com.solo_finance_app.inventory.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

    private String itemName;

    private Integer quantity;

    private Double price;

    private Boolean lowStock;
}