package com.solo_finance_app.budget.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetResponse {

    private String category;

    private Double monthlyLimit;

    private Double currentSpent;

    private Double remainingAmount;

    private Boolean exceeded;
}
