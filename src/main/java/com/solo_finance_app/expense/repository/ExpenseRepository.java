package com.solo_finance_app.expense.repository;

import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);
}
