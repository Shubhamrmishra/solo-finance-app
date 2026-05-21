package com.solo_finance_app.analytics.service;

import com.solo_finance_app.analytics.dt.MonthlySummaryResponse;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.expense.repository.
        ExpenseRepository;
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
public class AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public MonthlySummaryResponse getMonthlySummary(
            Double monthlyIncome
    ) {

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        LocalDate startDate =
                LocalDate.now().withDayOfMonth(1);

        LocalDate endDate =
                LocalDate.now();

        List<Expense> expenses =
                expenseRepository
                        .findByUserAndExpenseDateBetween(
                                user,
                                startDate,
                                endDate
                        );

        double totalExpense = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double savings = monthlyIncome - totalExpense;

        return MonthlySummaryResponse.builder()
                .totalExpense(totalExpense)
                .totalIncome(monthlyIncome)
                .totalSavings(savings)
                .build();
    }
}