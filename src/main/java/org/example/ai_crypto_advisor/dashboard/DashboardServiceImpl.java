package org.example.ai_crypto_advisor.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.ai_crypto_advisor.api_services.*;
import org.example.ai_crypto_advisor.userPreferences.ContentType;
import org.example.ai_crypto_advisor.userPreferences.UserPreferences;
import org.example.ai_crypto_advisor.userPreferences.UserPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardContentRepository dashboardContentRepository;
    @Autowired
    private UserPreferencesService userPreferencesService;
    @Autowired
    private CryptoPanicService cryptoPanicService;
    @Autowired
    private CoinGeckoService coinGeckoService;
    @Autowired
    private AiInsightService aiInsightService;
    @Autowired
    private MemeService memeService;


    @Override
    public Map<String, DashboardContent> createDashboardForUser(int userId) {
        UserPreferences preferences = userPreferencesService.getUserPreferencesByUserId(userId);
        if (preferences == null) {
            throw new RuntimeException("User not found");
        }
        dashboardContentRepository.deleteByUser(preferences.getUser());

        Map<String, DashboardContent> dashboardContent = new HashMap<>();
        Set<ContentType> wantedContent = preferences.getContentTypes();

        if (wantedContent.contains(ContentType.NEWS)){
            dashboardContent.put("news", createNewsContent(preferences));
        }
        if (wantedContent.contains(ContentType.MEME)){
            dashboardContent.put("meme", createMemeContent(preferences));
        }
        if (wantedContent.contains(ContentType.PRICES)){
            dashboardContent.put("prices", createPricesContent(preferences));
        }
        if (wantedContent.contains(ContentType.AI_INSIGHT)){
            dashboardContent.put("ai_insight", createAiInsightContent(preferences));
        }
        return dashboardContent;

    }

    @Override
    public Map<String, DashboardContent> getDashboardForUser(int userId) {
        return createDashboardForUser(userId);
    }

    @Override
    public Map<String, DashboardContent> getTodaysDashboard(int userId) {
        LocalDateTime startOfToday = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        List<DashboardContent> todayContent = dashboardContentRepository.
                findByCreatedAtAfterOrderByCreatedAtDesc(startOfToday);

        Map<String, DashboardContent> dashboardContent = new HashMap<>();
        for (DashboardContent content : todayContent) {
            String key = content.getContentType().name().toLowerCase();
            if (!dashboardContent.containsKey(key)) {
                dashboardContent.put(key, content);
            }
        }
        return dashboardContent;
    }

    @Override
    public DashboardContent createNewsContent(UserPreferences preferences) {
        List<NewsDto> newsData = cryptoPanicService.getNews(preferences.getInterestedAssets());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(newsData);

            DashboardContent content = new DashboardContent(
                    ContentType.NEWS,
                    jsonContent,
                    "CryptoPanic",
                    preferences.getUser()
            );
            return dashboardContentRepository.save(content);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing news DTOs", e);
        }
    }

    @Override
    public DashboardContent createPricesContent(UserPreferences preferences) {
        String pricesData = coinGeckoService.getPrices(preferences.getInterestedAssets());
        DashboardContent content = new DashboardContent(
                ContentType.PRICES,
                pricesData,
                "CoinGecko",
                preferences.getUser()
        );
        return dashboardContentRepository.save(content);
    }

    @Override
    public DashboardContent createAiInsightContent(UserPreferences preferences) {
        String insightData = aiInsightService.getAiInsight(preferences.getInterestedAssets());
        DashboardContent content = new DashboardContent(
                ContentType.AI_INSIGHT,
                insightData,
                "OpenRouter",
                preferences.getUser()
        );
        return dashboardContentRepository.save(content);
    }

    @Override
    public DashboardContent createMemeContent(UserPreferences preferences) {
        String memeUrl = memeService.getRandomMeme(preferences);
        DashboardContent content = new DashboardContent(
                ContentType.MEME,
                memeUrl,
                "static",
                preferences.getUser()

        );
        return dashboardContentRepository.save(content);
    }


}
