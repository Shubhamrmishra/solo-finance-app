package com.solo_finance_app.analytics.service;

import com.solo_finance_app.analytics.dt.CategoryExpenseResponse;
import com.solo_finance_app.analytics.dt.MonthlySummaryResponse;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.expense.repository.ExpenseRepository;
import com.solo_finance_app.user.CurrentUserService;
import com.solo_finance_app.user.User;
import com.solo_finance_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.solo_finance_app.common.Constants.EXPENSE_WARNING;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public MonthlySummaryResponse getMonthlySummary(Double monthlyIncome) {

        User user = currentUserService.getCurrentUser();

        LocalDate startDate = LocalDate.now().withDayOfMonth(1);

        LocalDate endDate = LocalDate.now();

        List<Expense> expenses = expenseRepository.findByUserAndExpenseDateBetween(user, startDate, endDate);

        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();

        double savings = monthlyIncome - totalExpense;

        if (totalExpense >= 3000){
            return MonthlySummaryResponse.builder().totalExpense(totalExpense).totalIncome(monthlyIncome).totalSavings(savings).Warning(EXPENSE_WARNING).build();
        }

        return MonthlySummaryResponse.builder().totalExpense(totalExpense).totalIncome(monthlyIncome).totalSavings(savings).build();
    }

    public List<CategoryExpenseResponse> getCategoryWiseExpenses() {

        User user = currentUserService.getCurrentUser();

        List<Object[]> results = expenseRepository.getCategoryWiseExpenses(user);

        return results.stream().map(result ->
                        CategoryExpenseResponse.builder()
                                .category((String) result[0])
                                .totalAmount(((Number) result[1]).doubleValue()).build()).toList();
    }
}