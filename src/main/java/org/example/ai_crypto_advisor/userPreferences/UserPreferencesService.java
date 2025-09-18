package org.example.ai_crypto_advisor.userPreferences;

import org.example.ai_crypto_advisor.user.User;

public interface UserPreferencesService {

    UserPreferences getUserPreferencesByUserId(int userId);
    UserPreferences addUserPreferences(int user, UserPreferences userPreferences);
}
