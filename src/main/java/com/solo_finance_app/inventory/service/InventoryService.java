package com.solo_finance_app.inventory.service;

import com.solo_finance_app.inventory.dto.*;
import com.solo_finance_app.inventory.entity.InventoryItem;
import com.solo_finance_app.inventory.repository.InventoryRepository;
import com.solo_finance_app.user.User;
import com.solo_finance_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.
        SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;

    public String addItem(InventoryRequest request) {

        String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        InventoryItem item = InventoryItem.builder()
                .itemName(request.getItemName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .lowStockThreshold(
                        request.getLowStockThreshold()
                )
                .user(user)
                .build();

        inventoryRepository.save(item);

        return "Inventory Item Added";
    }

    public List<InventoryResponse> getMyInventory() {

        String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        List<InventoryItem> items =
                inventoryRepository.findByUser(user);

        return items.stream()
                .map(item ->
                        InventoryResponse.builder()
                                .itemName(item.getItemName())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .lowStock(
                                        item.getQuantity()
                                                <= item.getLowStockThreshold()
                                )
                                .build()
                )
                .toList();
    }
}