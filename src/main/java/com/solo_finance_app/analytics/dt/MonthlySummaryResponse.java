package com.solo_finance_app.analytics.dt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlySummaryResponse {

    private Double totalExpense;

    private Double totalIncome;

    private Double totalSavings;

    private String Warning;
}
