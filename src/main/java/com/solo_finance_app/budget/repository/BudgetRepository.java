package com.solo_finance_app.budget.repository;


import com.solo_finance_app.budget.entity.Budget;
import com.solo_finance_app.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUserAndCategory(User user, String category);

    List<Budget> findByUser(User user);
}