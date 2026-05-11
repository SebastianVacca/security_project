package com.devcode.svacca.security_project.util;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String genrateToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        var keyBites = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBites);
    }

    public boolean validateToken(String token) {
        var exp = extractClaim(token, Claims::getExpiration);
        return (exp.before(new Date())) ? false : true;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        var claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseUnsecuredClaims(token)
                .getPayload();
        
                return claimsResolver.apply(claims);
    }

}
