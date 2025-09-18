package org.example.ai_crypto_advisor.userPreferences;

import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.user.UserRepository;
import org.example.ai_crypto_advisor.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPreferencesServiceImpl implements UserPreferencesService {
    @Autowired
    private UserPreferencesRepository userPreferencesRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public UserPreferences getUserPreferencesByUserId(int userId) {
        User user = userServiceImpl.getUserById(userId);
        return userPreferencesRepository.findByUser(user);
    }

    @Override
    public UserPreferences addUserPreferences(int user, UserPreferences userPreferences) {
        User user1 = userServiceImpl.getUserById(user);
        UserPreferences savedUserPref = new UserPreferences();
        savedUserPref.setUser(user1);
        savedUserPref.setContentTypes(userPreferences.getContentTypes());
        savedUserPref.setInterestedAssets(userPreferences.getInterestedAssets());
        savedUserPref.setInvestorType(userPreferences.getInvestorType());

        return userPreferencesRepository.save(savedUserPref);
    }
}
