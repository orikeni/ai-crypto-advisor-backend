package org.example.ai_crypto_advisor.api_services;

import java.util.Set;

public interface CoinGeckoService {

    String getPrices(Set<String> interestedAssets);
}
