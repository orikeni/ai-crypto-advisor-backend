package org.example.ai_crypto_advisor.dashboard;

import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.userPreferences.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DashboardContentRepository extends JpaRepository<DashboardContent, Integer> {

    List<DashboardContent> findByContentType(ContentType contentType);
    List<DashboardContent> findByApiSource(String apiSource);
    List<DashboardContent> findByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime since);
    List<DashboardContent> findByUserId(int userId);
    List<DashboardContent> findByUserIdAndCreatedAtAfterOrderByCreatedAtDesc(int userId, LocalDateTime since);
    void deleteByUser(User user);


}
