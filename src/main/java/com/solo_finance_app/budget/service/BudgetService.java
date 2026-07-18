package com.solo_finance_app.budget.service;

import com.solo_finance_app.budget.dto.*;
import com.solo_finance_app.budget.entity.Budget;
import com.solo_finance_app.budget.repository.BudgetRepository;
import com.solo_finance_app.expense.repository.ExpenseRepository;

import com.solo_finance_app.user.CurrentUserService;
import com.solo_finance_app.user.User;
import com.solo_finance_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.
        SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;


    public String createBudget(BudgetRequest request) {

        User user = currentUserService.getCurrentUser();

        Budget budget = Budget.builder()
                .category(request.getCategory())
                .monthlyLimit(request.getMonthlyLimit())
                .user(user)
                .build();

        budgetRepository.save(budget);

        return "Budget Created Successfully";
    }

    public List<BudgetResponse> getMyBudgets() {

        User user = currentUserService.getCurrentUser();

        List<Budget> budgets = budgetRepository.findByUser(user);

        LocalDate startDate = LocalDate.now().withDayOfMonth(1);

        LocalDate endDate = LocalDate.now();

        return budgets.stream().map(budget -> {
            Double spent = expenseRepository
                    .getTotalExpenseByCategoryAndDate(user, budget.getCategory(), startDate, endDate);

            double remaining = budget.getMonthlyLimit() - spent;

            return BudgetResponse.builder()
                            .category(budget.getCategory())
                            .monthlyLimit(budget.getMonthlyLimit())
                            .currentSpent(spent)
                            .remainingAmount(remaining)
                            .exceeded(remaining < 0)
                            .build();
        }).toList();
    }
}