package com.solo_finance_app.expense.repository;

import com.solo_finance_app.expense.dto.response.ExpenseResponse;
import com.solo_finance_app.expense.entity.Expense;
import com.solo_finance_app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<Expense, Long> {

    List<ExpenseResponse> findByUser(User user);

    List<Expense> findByUserAndExpenseDateBetween(User user, LocalDate startDate, LocalDate endDate);

    @Query("""
       SELECT e.category, SUM(e.amount)
       FROM Expense e
       WHERE e.user = :user
       GROUP BY e.category
       """)
    List<Object[]> getCategoryWiseExpenses(User user);

    @Query("""
       SELECT COALESCE(SUM(e.amount),0)
       FROM Expense e
       WHERE e.user = :user
       AND e.category = :category
       AND e.expenseDate BETWEEN :startDate AND :endDate
       """)
    Double getTotalExpenseByCategoryAndDate(@Param("user") User user, @Param("category") String category,
            @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
