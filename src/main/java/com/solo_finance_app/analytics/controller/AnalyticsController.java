package com.solo_finance_app.analytics.controller;

import com.solo_finance_app.analytics.dt.CategoryExpenseResponse;
import com.solo_finance_app.analytics.dt.MonthlySummaryResponse;
import com.solo_finance_app.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/monthly-summary")
    public MonthlySummaryResponse getMonthlySummary(@RequestParam Double income) {
        return analyticsService.getMonthlySummary(income);
    }

    @GetMapping("/category-wise")
    public List<CategoryExpenseResponse> getCategoryWiseExpenses() {

        return analyticsService.getCategoryWiseExpenses();
    }
}
