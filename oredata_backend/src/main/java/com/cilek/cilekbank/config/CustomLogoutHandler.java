package com.cilek.cilekbank.config;

import com.cilek.cilekbank.model.Token;
import com.cilek.cilekbank.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@AllArgsConstructor
@Configuration
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("Logout handler is called");

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return;
        }

        jwtToken = authorizationHeader.substring(7);
        Token token = tokenRepository.findByAccessToken(jwtToken).orElse(null);

        if (token == null) {
            System.out.println("Token is not found");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (token.isLoggedOut()) {
            System.out.println("Token is already logged out");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        token.setLoggedOut(true);
        tokenRepository.save(token);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
