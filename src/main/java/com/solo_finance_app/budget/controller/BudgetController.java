package com.solo_finance_app.budget.controller;

import com.solo_finance_app.budget.dto.*;
import com.solo_finance_app.budget.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public String createBudget(@RequestBody BudgetRequest request) {
        return budgetService.createBudget(request);
    }

    @GetMapping
    public List<BudgetResponse> getMyBudgets() {
        return budgetService.getMyBudgets();
    }
}
