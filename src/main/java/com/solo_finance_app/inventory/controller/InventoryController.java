package com.solo_finance_app.inventory.controller;

import com.solo_finance_app.inventory.dto.*;
import com.solo_finance_app.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public String addItem(@RequestBody InventoryRequest request) {
        return inventoryService.addItem(request);
    }

    @GetMapping
    public List<InventoryResponse> getMyInventory() {

        return inventoryService.getMyInventory();
    }
}