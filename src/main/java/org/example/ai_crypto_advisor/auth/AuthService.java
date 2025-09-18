package org.example.ai_crypto_advisor.auth;

import org.example.ai_crypto_advisor.config.LoginRequest;
import org.example.ai_crypto_advisor.config.TokenConfig;
import org.example.ai_crypto_advisor.config.TokenResponse;
import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenConfig tokenConfig;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenResponse login(LoginRequest loginRequest) {
        boolean isLoginValid = this.isLoginValid(loginRequest);
        if (isLoginValid) {
            User user = (User) this.userService.loadUserByUsername(loginRequest.getEmail());
            String token = this.tokenConfig.generateToken(user);
            return new TokenResponse(token);
        }
        return null;
    }

    public boolean isLoginValid(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            System.out.println("Invalid email or password");
            return false;
        }
        return true;
    }

    public TokenResponse register(User user) {
        System.out.println(user.getName()+" "+user.getPassword());
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userFromDb = this.userService.addUser(user);
        String token = this.tokenConfig.generateToken(userFromDb);
        return new TokenResponse(token);
    }
}
