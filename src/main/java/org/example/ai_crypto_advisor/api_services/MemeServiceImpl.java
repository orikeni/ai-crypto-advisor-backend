package org.example.ai_crypto_advisor.api_services;

import org.example.ai_crypto_advisor.userPreferences.UserPreferences;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class MemeServiceImpl implements MemeService {

    private static final String[] MEME_URLS = {
            "https://i.imgur.com/crypto-meme1.jpg",
            "https://i.imgur.com/crypto-meme2.jpg",
            "https://i.imgur.com/crypto-meme3.jpg"
    };

    @Override
    public String getRandomMeme(UserPreferences userPreferences) {
        try {
            int i = ThreadLocalRandom.current().nextInt(MEME_URLS.length);
            return MEME_URLS[i];
        } catch (Exception e) {
            return "https://via.placeholder.com/400x300?text=Crypto+Meme+Loading...";
        }
    }
}
