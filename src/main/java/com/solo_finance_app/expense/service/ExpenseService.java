package com.solo_finance_app.expense.service;

import com.solo_finance_app.expense.dto.request.ExpenseRequest;
import com.solo_finance_app.expense.dto.response.ExpenseResponse;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.expense.repository.ExpenseRepository;
import com.solo_finance_app.user.CurrentUserService;
import com.solo_finance_app.user.User;
import com.solo_finance_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public String addExpense(ExpenseRequest request) {

        User user = currentUserService.getCurrentUser();

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(request.getCategory())
                .note(request.getNote())
                .expenseDate(request.getExpenseDate())
                .user(user)
                .build();

        expenseRepository.save(expense);

        return "Expense Added Successfully";
    }

    public List<ExpenseResponse> getMyExpenses() {

        User user = currentUserService.getCurrentUser();

        return expenseRepository.findByUser(user)
                .stream()
                .map(expense -> ExpenseResponse.builder()
                        .id(expense.getId())
                        .title(expense.getTitle())
                        .amount(expense.getAmount())
                        .category(expense.getCategory())
                        .note(expense.getNote())
                        .expenseDate(expense.getExpenseDate())
                        .build())
                .toList();
    }
}
