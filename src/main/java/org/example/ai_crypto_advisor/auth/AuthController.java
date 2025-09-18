package org.example.ai_crypto_advisor.auth;
import jakarta.servlet.http.HttpServletRequest;
import org.example.ai_crypto_advisor.config.LoginRequest;
import org.example.ai_crypto_advisor.config.TokenResponse;
import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenResponse login (@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/registration")
    public TokenResponse registration (@RequestBody UserDTO user) {
        System.out.println(user.getName()+" "+user.getPassword());
        User saved = new User();
        saved.setName(user.getName());
        saved.setPassword(user.getPassword());
        saved.setEmail(user.getEmail());
        System.out.println(saved.getName()+" "+saved.getPassword());
        return authService.register(saved);
    }

    @GetMapping("/ping")
    public String ping() { return "ok"; }

}
