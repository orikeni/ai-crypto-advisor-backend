package org.example.ai_crypto_advisor.dashboard;

import org.example.ai_crypto_advisor.userPreferences.UserPreferences;

import java.util.Map;

public interface DashboardService {

    Map<String, DashboardContent> createDashboardForUser(int userId);
    Map<String, DashboardContent> getDashboardForUser(int userId);
    Map<String, DashboardContent> getTodaysDashboard(int userId);
    DashboardContent createNewsContent(UserPreferences preferences);
    DashboardContent createPricesContent(UserPreferences preferences);
    DashboardContent createAiInsightContent(UserPreferences preferences);
    DashboardContent createMemeContent(UserPreferences preferences);

}
