package org.example.ai_crypto_advisor.userPreferences;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.ai_crypto_advisor.user.User;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnore
    private User user;

    @ElementCollection
    @CollectionTable(name = "user_pref_assets", joinColumns = @JoinColumn(name = "pref_id"))
    @Column(name = "asset")
    private Set<String> interestedAssets = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private InvestorType investorType;

    @ElementCollection(targetClass = ContentType.class)
    @CollectionTable(name = "user_pref_content", joinColumns = @JoinColumn(name = "pref_id"))
    @Column(name = "content_type")
    @Enumerated(EnumType.STRING)
    private Set<ContentType> contentTypes = new HashSet<>();

    public UserPreferences() {
    }

    public UserPreferences(int id, User user, Set<String> interestedAssets, InvestorType investorType,
                           Set<ContentType> contentTypes) {
        this.id = id;
        this.user = user;
        this.interestedAssets = interestedAssets;
        this.investorType = investorType;
        this.contentTypes = contentTypes;
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

    public Set<String> getInterestedAssets() {
        return interestedAssets;
    }

    public void setInterestedAssets(Set<String> interestedAssets) {
        this.interestedAssets = interestedAssets;
    }

    public InvestorType getInvestorType() {
        return investorType;
    }

    public void setInvestorType(InvestorType investorType) {
        this.investorType = investorType;
    }

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(Set<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }
}
