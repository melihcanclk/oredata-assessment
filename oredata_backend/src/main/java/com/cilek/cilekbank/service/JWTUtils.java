package com.cilek.cilekbank.service;

import com.cilek.cilekbank.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.function.Function;

@Configuration
public class JWTUtils {

    private final SecretKey secretKey;

    @Autowired
    private final TokenRepository tokenRepository;
    // 24 hours
    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    public JWTUtils(TokenRepository tokenRepository) {
        // generate secret key
        String secret = "d3716c9ffebec2fa6183c5905e9ae1594ca49daba4d1caee4805217af7137300";
        byte[] decodedKey = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(decodedKey, "HmacSHA256");
        this.tokenRepository = tokenRepository;
    }

    public String generateAccessToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new java.util.Date(System.currentTimeMillis()))
                .expiration(new java.util.Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T getAllClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenLoggedOut(token));
    }

    private boolean isTokenLoggedOut(String token) {
        return tokenRepository.findByAccessToken(token).isEmpty();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token);
    }

    private boolean getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token, Claims::getExpiration).before(new java.util.Date());
    }
}
