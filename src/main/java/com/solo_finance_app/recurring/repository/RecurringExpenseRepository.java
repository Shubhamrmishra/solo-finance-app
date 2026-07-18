package com.solo_finance_app.recurring.repository;

import com.solo_finance_app.recurring.entity.RecurringExpense;
import com.solo_finance_app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense, Long> {

    List<RecurringExpense> findByUser(User user);
}