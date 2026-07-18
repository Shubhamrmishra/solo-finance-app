package com.solo_finance_app.expense.service;

import com.solo_finance_app.expense.dto.ExpenseRequest;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.expense.repository.ExpenseRepository;
import com.solo_finance_app.user.CurrentUserService;
import com.solo_finance_app.user.User;
import com.solo_finance_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.
        SecurityContextHolder;
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

    public List<Expense> getMyExpenses() {

        User user = currentUserService.getCurrentUser();

        return expenseRepository.findByUser(user);
    }
}
