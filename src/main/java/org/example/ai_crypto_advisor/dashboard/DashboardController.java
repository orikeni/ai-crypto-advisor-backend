package org.example.ai_crypto_advisor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{userId}")
    public Map<String, DashboardContent> getDashboard(@PathVariable int userId) {
        return dashboardService.getDashboardForUser(userId);
    }

    @PostMapping("/{userId}/refresh")
    public Map<String, DashboardContent> refreshDashboard(@PathVariable int userId) {
        return dashboardService.createDashboardForUser(userId);
    }
}
