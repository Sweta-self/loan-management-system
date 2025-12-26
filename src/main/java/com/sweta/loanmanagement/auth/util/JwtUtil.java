package com.sweta.loanmanagement.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    //secretkey creation
    private SecretKey getSigningKey(){
        return
                Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt( new Date())
                .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }
    public String extractUsername(String token){
        return getClaims(token).getSubject();

    }
    public boolean validateToken(String token, UserDetails userDetails){
       return extractUsername(token).equals(userDetails.getUsername())
               &&!isTokenExpired(token);
    }
private boolean isTokenExpired(String token){
        return getClaims(token)
                .getExpiration()
                .before(new Date());
}
    //claims parsing
    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
