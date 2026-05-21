package com.solo_finance_app.expense.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseRequest {

    private String title;

    private Double amount;

    private String category;

    private String note;

    private LocalDate expenseDate;
}
