package org.example.ai_crypto_advisor.api_services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Service
public class AiInsightServiceImpl implements AiInsightService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${openrouter.api.key}")
    private String apiKey;

    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";


    @Override
    public String getAiInsight(Set<String> interestedAssets) {
        try{
            String prompt = createPrompt(interestedAssets);
            String requestBody = createRequestBody(prompt);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);
            HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

            System.out.println("OpenRouter Response: " + response.getBody());


            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String insight = parseResponse(response.getBody());
                return insight != null ? insight : generateMockInsight(interestedAssets);
            }

        }catch (Exception e){
            e.printStackTrace();
            return generateMockInsight(interestedAssets);
        }
        return generateMockInsight(interestedAssets);
    }

    private String createPrompt(Set<String> assets) {
        if (assets == null || assets.isEmpty()) {
            return "Give a brief crypto market insight in 2 sentences.";
        }

        String assetList = String.join(", ", assets);
        return "Give a brief analysis for " + assetList + " in 2 sentences.";
    }

    private String createRequestBody(String prompt) {
        return "{\n" +
                "  \"model\": \"mistralai/mistral-7b-instruct\",\n" +
                "  \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}],\n" +
                "  \"max_tokens\": 150,\n" +
                "  \"temperature\": 0.7\n" +
                "}";
    }

    private String parseResponse(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode choice = root.path("choices").get(0);

            String text = null;
            if (choice.has("message") && choice.get("message").has("content")) {
                text = choice.get("message").get("content").asText().trim();
            } else if (choice.has("text")) {
                text = choice.get("text").asText().trim();
            }

            if (text != null) {
                return text.replace("<s>", "")
                        .replace("</s>", "")
                        .replace("[OUT]", "")
                        .replace("[/OUT]", "")
                        .trim();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateMockInsight(Set<String> assets) {
        if (assets == null || assets.isEmpty()) {
            return "Today's AI Insight: Crypto market shows mixed signals. Trade carefully!";
        }

        String assetList = String.join(", ", assets);
        return "AI Analysis: " + assetList + " shows potential. Consider market trends and diversify your portfolio.";
    }
}
