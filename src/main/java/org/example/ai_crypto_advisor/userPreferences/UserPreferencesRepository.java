package org.example.ai_crypto_advisor.userPreferences;

import org.example.ai_crypto_advisor.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Integer> {
    UserPreferences findByUser(User user);
    boolean existsByUser(User user);
}
