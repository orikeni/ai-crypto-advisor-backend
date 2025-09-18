package org.example.ai_crypto_advisor.votes;

import org.example.ai_crypto_advisor.dashboard.DashboardContent;
import org.example.ai_crypto_advisor.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentVoteRepository extends JpaRepository<ContentVote, Integer> {

    ContentVote findByUserAndDashboardContent(User user, DashboardContent dashboardContent);
    int countByDashboardContentAndVoteType(DashboardContent dashboardContent, VoteType voteType);
    ContentVote findByUserIdAndDashboardContentId(int userId, int dashboardContentId);
    int countByDashboardContentIdAndVoteType(int dashboardContentId, VoteType voteType);

}
