package org.example.ai_crypto_advisor.api_services;

import java.util.List;
import java.util.Set;

public interface CryptoPanicService {
    List<NewsDto> getNews(Set<String> interestedAssets);
}
