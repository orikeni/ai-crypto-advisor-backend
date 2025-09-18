package org.example.ai_crypto_advisor.api_services;

import org.example.ai_crypto_advisor.userPreferences.UserPreferences;

public interface MemeService {

    String getRandomMeme(UserPreferences userPreferences);
}
