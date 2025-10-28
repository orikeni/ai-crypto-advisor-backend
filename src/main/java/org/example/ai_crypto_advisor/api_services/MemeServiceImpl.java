package org.example.ai_crypto_advisor.api_services;

import org.example.ai_crypto_advisor.userPreferences.UserPreferences;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MemeServiceImpl implements MemeService {

    private static final String[] MEME_URLS = {
            "http://localhost:8080/memes/meme1.jpg",
            "http://localhost:8080/memes/meme2.jpg",
            "http://localhost:8080/memes/meme3.jpg",
            "http://localhost:8080/memes/meme4.jpg",
            "http://localhost:8080/memes/meme5.jpg",
            "http://localhost:8080/memes/meme6.jpg",
            "http://localhost:8080/memes/meme7.jpg",
    };

    @Override
    public String getRandomMeme(Set<String> interestedAssets) {
        try {
            int i = ThreadLocalRandom.current().nextInt(MEME_URLS.length);
            return MEME_URLS[i];
        } catch (Exception e) {
            return "/memes/meme1.jpg";
        }
    }
}
