package com.solo_finance_app.analytics.controller;

import com.solo_finance_app.analytics.dt.MonthlySummaryResponse;
import com.solo_finance_app.analytics.service.
        AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/monthly-summary")
    public MonthlySummaryResponse getMonthlySummary(
            @RequestParam Double income
    ) {

        return analyticsService
                .getMonthlySummary(income);
    }
}
