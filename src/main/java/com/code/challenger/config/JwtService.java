package com.code.challenger.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyMap;

@Service
public class JwtService {
    private final static String key = "0e265274a1a3995f3389485b76d2c41bcf87eab3e800c3b614e0d41ac263176f";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder().setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 10000 *60*24))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setClaims(emptyMap())
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 10000 *60*24))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> function) {
        final Claims claims = extractAllClaims(token);
        return function.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build().parseClaimsJwt(token)
            .getBody();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
