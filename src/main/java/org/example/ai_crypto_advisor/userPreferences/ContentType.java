package org.example.ai_crypto_advisor.userPreferences;

public enum ContentType {
    NEWS("Market News"),
    PRICES("Coin Prices"),
    AI_INSIGHT("AI Insight of the Day"),
    MEME("Fun Crypto Meme");

    private final String displayName;

    ContentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
