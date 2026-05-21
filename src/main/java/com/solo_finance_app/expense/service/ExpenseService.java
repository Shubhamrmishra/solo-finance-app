package com.solo_finance_app.expense.service;

import com.solo_finance_app.expense.dto.ExpenseRequest;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.expense.repository.ExpenseRepository;
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

    public String addExpense(ExpenseRequest request) {

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

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

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return expenseRepository.findByUser(user);
    }
}
