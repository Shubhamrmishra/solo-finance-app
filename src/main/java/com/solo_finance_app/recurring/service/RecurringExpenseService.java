package com.solo_finance_app.recurring.service;

import com.solo_finance_app.recurring.dto.RecurringExpenseRequest;
import com.solo_finance_app.recurring.entity.RecurringExpense;
import com.solo_finance_app.recurring.repository.RecurringExpenseRepository;
import com.solo_finance_app.user.User;
import com.solo_finance_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.
        SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecurringExpenseService {

    private final RecurringExpenseRepository
            recurringExpenseRepository;

    private final UserRepository userRepository;

    public String createRecurringExpense(RecurringExpenseRequest request) {

        String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        RecurringExpense recurringExpense = RecurringExpense.builder()
                        .title(request.getTitle())
                        .amount(request.getAmount())
                        .category(request.getCategory())
                        .frequency(request.getFrequency())
                        .nextDueDate(request.getNextDueDate())
                        .user(user)
                        .build();

        recurringExpenseRepository.save(recurringExpense);

        return "Recurring Expense Created";
    }

    public List<RecurringExpense> getRecurringExpenses() {

        String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return recurringExpenseRepository.findByUser(user);
    }
}