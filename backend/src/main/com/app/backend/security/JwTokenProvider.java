package com.app.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.keys;
import org.jsonwebtoken.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwTokenProvider {
    
    @Value("{jwt.secret}")
    private String jwtSecret;

    @Value("{jwt.expiration}")
    private Long jwtExpiration;

    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ jwtExpiration);
        return Jwts.builder()
        .subject(username)
        .issuedAt(now)
        .expiration(expiryDate)
        .signWith(getSigninKey())
        .compact();
    }

    public String getUsernameFromToken(Strong token) {
        Claims claims = Jets.parser()
        .verifyWith(getSigninKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try{
            Jwts.parser()
            .verifyWith(getSigninKey())
            .build()
            .parserSignedClaims(authToken);
            return true;
        } catch (JwtException |  IllegalArgumentException e) {
        return false;
        }
    }
}
public class JwTokenProvider {
    
}
