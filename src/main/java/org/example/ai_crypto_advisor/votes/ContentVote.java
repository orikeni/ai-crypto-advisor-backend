package org.example.ai_crypto_advisor.votes;

import jakarta.persistence.*;
import org.example.ai_crypto_advisor.dashboard.DashboardContent;
import org.example.ai_crypto_advisor.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "content_votes")
public class ContentVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_content_id", nullable = false)
    private DashboardContent dashboardContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote_type", nullable = false)
    private VoteType voteType;

    @Column(name = "voted_at", nullable = false)
    private LocalDateTime votedAt;

    public ContentVote() {
        this.votedAt = LocalDateTime.now();
    }

    public ContentVote(User user, DashboardContent dashboardContent, VoteType voteType) {
        this.user = user;
        this.dashboardContent = dashboardContent;
        this.voteType = voteType;
        this.votedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DashboardContent getDashboardContent() {
        return dashboardContent;
    }

    public void setDashboardContent(DashboardContent dashboardContent) {
        this.dashboardContent = dashboardContent;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(LocalDateTime votedAt) {
        this.votedAt = votedAt;
    }
}
