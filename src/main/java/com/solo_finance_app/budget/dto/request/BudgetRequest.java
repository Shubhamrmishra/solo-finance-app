package com.solo_finance_app.budget.dto.request;

import lombok.Data;

@Data
public class BudgetRequest {

    private String category;

    private Double monthlyLimit;
}
