package com.solo_finance_app.recurring.dto;

import com.solo_finance_app.recurring.entity.Frequency;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecurringExpenseRequest {

    private String title;

    private Double amount;

    private String category;

    private Frequency frequency;

    private LocalDate nextDueDate;
}