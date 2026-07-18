package com.solo_finance_app.expense.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {

    private Long id;

    private String title;

    private Double amount;

    private String category;

    private String note;

    private LocalDate expenseDate;
}
