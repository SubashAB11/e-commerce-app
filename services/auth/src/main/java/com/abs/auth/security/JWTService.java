package com.abs.auth.security;

import com.abs.auth.auth.AuthenticationResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    private static final String TOKEN_TYPE = "token_type";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    @Value("${app.security.jwt.access-token-exp}")
    private long accessTokenExpiration;
    @Value("${app.security.jwt.refresh-token-exp}")
    private long refreshTokenExpiration;

    public JWTService() throws Exception {
        this.privateKey = KeyUtils.loadPrivateKey("keys/private-key.pem");
        this.publicKey = KeyUtils.loadPublicKey("keys/public-key.pem");
    }

    public String generateAccessToken(String userName) {
        final Map<String, Object> claims = Map.of(TOKEN_TYPE, ACCESS_TOKEN);
        return buildToken(userName, claims, this.accessTokenExpiration);
    }

    public String generateRefreshToken(String userName) {
        final Map<String, Object> claims = Map.of(TOKEN_TYPE, REFRESH_TOKEN);
        return buildToken(userName, claims, this.refreshTokenExpiration);
    }

    public String buildToken(String userName, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .claims(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(privateKey)
                .compact();
    }

    public boolean isTokenValid(String token, String expectedUserName) {
        String userName = extractUserName(token);
        return userName.equals(expectedUserName) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public String extractUserName(String token) {
        return extractClaims(token).getSubject();
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(this.publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Invalid Token", e);
        }
    }

    public AuthenticationResponse refreshAccessToken(String refreshToken) {
        Claims claims = extractClaims(refreshToken);
        if(!REFRESH_TOKEN.equals(claims.get(TOKEN_TYPE))) throw new RuntimeException("Invalid token type");
        if(isTokenExpired(refreshToken)) throw new RuntimeException("Refresh token expired");
        String tokenType = "Bearer";
        return  new AuthenticationResponse(generateAccessToken(claims.getSubject()), generateRefreshToken(claims.getSubject()), tokenType);
    }


}
