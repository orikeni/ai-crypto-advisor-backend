package org.example.ai_crypto_advisor.userPreferences;


public interface UserPreferencesService {

    UserPreferences getUserPreferencesByUserId(int userId);
    UserPreferences addUserPreferences(int user, UserPreferences userPreferences);
}
