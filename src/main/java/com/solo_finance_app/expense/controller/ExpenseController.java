package com.solo_finance_app.expense.controller;

import com.solo_finance_app.expense.dto.ExpenseRequest;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public String addExpense(
            @RequestBody ExpenseRequest request) {

        return expenseService.addExpense(request);
    }

    @GetMapping
    public List<Expense> getMyExpenses() {

        return expenseService.getMyExpenses();
    }
}
