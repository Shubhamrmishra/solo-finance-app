package com.solo_finance_app.analytics.dt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryExpenseResponse {

    private String category;

    private Double totalAmount;
}