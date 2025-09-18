package org.example.ai_crypto_advisor.api_services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CryptoPanicServiceImpl implements CryptoPanicService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${cryptopanic.api.key}")
    private String apiKey;
    

    private static final String API_URL = "https://cryptopanic.com/api/developer/v2/posts/";

    @Override
    public List<NewsDto> getNews(Set<String> interestedAssets) {
        try{
            String url = API_URL
                    + "?auth_token=" + apiKey
                    + "&public=true";
            String body = restTemplate.getForObject(url, String.class);
            if (body == null || body.isBlank()) {
                return getFallbackNews();
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(body);
            ArrayNode results = (ArrayNode) root.get("results");

            List<NewsDto> simplifiedNews = new ArrayList<>();
            for (JsonNode item : results) {
                String title = item.get("title").asText();
                String description = item.hasNonNull("description") ? item.get("description").asText() : "";
                String publishedAt = item.get("published_at").asText();

                simplifiedNews.add(new NewsDto(title, description, publishedAt));
            }

            return simplifiedNews.size() > 5 ? simplifiedNews.subList(0, 5) : simplifiedNews;

        } catch (Exception e) {
            return getFallbackNews();
        }
    }
    private List<NewsDto> getFallbackNews() {
        return List.of(
                new NewsDto(
                        "Bitcoin update",
                        "BTC continues to show strength",
                        "2025-09-17T00:00:00Z"
                )
        );
    }

}

