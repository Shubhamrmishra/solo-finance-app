package com.solo_finance_app.inventory.entity;

import com.solo_finance_app.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer quantity;

    private Double price;

    private Integer lowStockThreshold;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}