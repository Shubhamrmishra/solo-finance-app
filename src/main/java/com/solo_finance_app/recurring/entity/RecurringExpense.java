package com.solo_finance_app.recurring.entity;

import com.solo_finance_app.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "recurring_expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Double amount;

    private String category;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private LocalDate nextDueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}