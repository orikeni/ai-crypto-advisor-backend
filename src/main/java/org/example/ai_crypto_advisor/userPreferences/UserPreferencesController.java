package org.example.ai_crypto_advisor.userPreferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pref")
public class UserPreferencesController {

    @Autowired
    private UserPreferencesService userPreferencesService;

    @GetMapping("/{id}")
    public UserPreferences getUserPreferences(@PathVariable int id) {
        return userPreferencesService.getUserPreferencesByUserId(id);
    }

    @PostMapping("/{id}")
    public UserPreferences saveUserPreferences(@RequestBody UserPreferences userPreferences, @PathVariable int id) {
        return userPreferencesService.addUserPreferences(id, userPreferences);
    }
}
