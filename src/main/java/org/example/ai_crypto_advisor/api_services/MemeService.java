package org.example.ai_crypto_advisor.api_services;

import org.example.ai_crypto_advisor.userPreferences.UserPreferences;

import java.util.Set;

public interface MemeService {

    String getRandomMeme(Set<String> interestedAssets);
}
