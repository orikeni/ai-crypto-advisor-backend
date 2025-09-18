package org.example.ai_crypto_advisor.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ai_crypto_advisor.user.User;
import org.example.ai_crypto_advisor.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenConfig tokenConfig;
    @Autowired
    UserServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }
        else {
            String token = tokenHeader.replace("Bearer ", "");
            try {
                String userName = tokenConfig.getUserNameFromToken(token);
                User user = (User) this.userService.loadUserByUsername(userName);
                SecurityContextHolder.getContext().setAuthentication
                        (new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
                filterChain.doFilter(request, response);
            }
            catch (SignatureException signatureException) {
                System.out.println(signatureException.getMessage());
            }
        }

    }
}
