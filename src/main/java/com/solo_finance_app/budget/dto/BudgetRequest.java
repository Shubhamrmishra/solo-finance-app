package com.solo_finance_app.budget.dto;

import lombok.Data;

@Data
public class BudgetRequest {

    private String category;

    private Double monthlyLimit;
}
