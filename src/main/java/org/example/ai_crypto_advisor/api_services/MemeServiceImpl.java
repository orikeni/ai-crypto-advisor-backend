package org.example.ai_crypto_advisor.api_services;

import org.example.ai_crypto_advisor.userPreferences.UserPreferences;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MemeServiceImpl implements MemeService {

    private static final String BASE_URL = System.getenv().getOrDefault(
            "BACKEND_BASE_URL",
            "http://localhost:8080"
    );

    private static final String[] MEME_NAMES = {
            "meme1.jpg",
            "meme2.jpg",
            "meme3.jpg",
            "meme4.jpg",
            "meme5.jpg",
            "meme6.jpg",
            "meme7.jpg",
    };

    @Override
    public String getRandomMeme(Set<String> interestedAssets) {
        try {
            int i = ThreadLocalRandom.current().nextInt(MEME_NAMES.length);
            return BASE_URL + "/memes/" + MEME_NAMES[i];
        } catch (Exception e) {
            return BASE_URL + "/memes/meme1.jpg";
        }
    }
}
