package com.solo_finance_app.recurring.controller;

import com.solo_finance_app.recurring.dto.RecurringExpenseRequest;
import com.solo_finance_app.recurring.entity.RecurringExpense;
import com.solo_finance_app.recurring.service.RecurringExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-expenses")
@RequiredArgsConstructor
public class RecurringExpenseController {

    private final RecurringExpenseService recurringExpenseService;

    @PostMapping
    public String createRecurringExpense(@RequestBody RecurringExpenseRequest request) {
        return recurringExpenseService.createRecurringExpense(request);
    }

    @GetMapping
    public List<RecurringExpense> getRecurringExpenses() {
        return recurringExpenseService.getRecurringExpenses();
    }
}