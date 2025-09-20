package org.example.ai_crypto_advisor.auth;
import jakarta.servlet.http.HttpServletRequest;
import org.example.ai_crypto_advisor.config.LoginRequest;
import org.example.ai_crypto_advisor.config.TokenResponse;
import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = authService.login(loginRequest);
        if (tokenResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserDTO user) {
        User saved = new User();
        saved.setName(user.getName());
        saved.setPassword(user.getPassword());
        saved.setEmail(user.getEmail());

        TokenResponse tokenResponse = authService.register(saved);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/ping")
    public String ping() { return "ok"; }

}
