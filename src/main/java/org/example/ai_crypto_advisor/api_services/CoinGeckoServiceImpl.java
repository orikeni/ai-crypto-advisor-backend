package org.example.ai_crypto_advisor.api_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CoinGeckoServiceImpl implements CoinGeckoService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${coingecko.api.key}")
    private String apiKey;

    @Value("${prices.live:false}")
    private boolean pricesLive;


    private static final String API_URL = "https://api.coingecko.com/api/v3/simple/price";

    @Override
    public String getPrices(Set<String> interestedAssets){
        if (!pricesLive) return getFallbackPrices();
        try {
            Set<String> coinGeckoAssets = convertToCoinGeckoFormat(interestedAssets);
            String assetsParam = String.join(",", coinGeckoAssets);
            String url = API_URL + "?ids=" + assetsParam + "&vs_currencies=usd";

            HttpHeaders headers = new HttpHeaders();
            if (apiKey != null && !apiKey.isBlank()) headers.add("x-cg-demo-api-key", apiKey);

            ResponseEntity<String> resp =
                    restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);

            return (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null)
                    ? resp.getBody()
                    : getFallbackPrices();
        } catch (Exception e) {
            System.err.println("CoinGecko Error: " + e.getMessage());
            return getFallbackPrices();
        }
    }

    private Set<String> convertToCoinGeckoFormat(Set<String> assets) {
        Set<String> converted = new HashSet<>();
        for (String asset : assets) {
            switch (asset.toUpperCase()) {
                case "BTC": converted.add("bitcoin");
                break;
                case "ETH": converted.add("ethereum");
                break;
                case "SOL": converted.add("solana");
                break;
                default: converted.add(asset.toLowerCase());
            }
        } return converted;
    }

    private String getFallbackPrices() {
        return "{\"bitcoin\":{\"usd\":45000},\"ethereum\":{\"usd\":3000}}";
    }
}
